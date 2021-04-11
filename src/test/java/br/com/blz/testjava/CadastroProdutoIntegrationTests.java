package br.com.blz.testjava;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.domain.repository.ProdutoRepository;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.service.CadastroProdutoService;
import br.com.blz.testjava.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroProdutoIntegrationTests {

	@LocalServerPort
	private int port;
	private String jsonProdutoCorreto;
	private String jsonProdutoExistente;
	private String jsonAtualizaProdutoExistente;
	private Produto produtoCadastrado_1;
	private Produto produtoJaCadastrado;
	private Produto produtoCadastradoComDuplicidade;
	@Autowired
	private CadastroProdutoService produtoService;
	@Autowired
	private ProdutoRepository produtoRepository;

	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/produtos";

		jsonProdutoCorreto = ResourceUtils.getContentFromResource("/json/correto/cadastroProduto.json");
		jsonProdutoExistente = ResourceUtils.getContentFromResource("/json/correto/cadastroProdutoExistente.json");
		jsonAtualizaProdutoExistente = ResourceUtils.getContentFromResource("/json/correto/atualizarProdutoExistente.json");
		
		produtoRepository.deleteAll();
	}
	
	

	@Test
	public void deveRetornarStatus201_QuandoCadastrarProduto() {

		RestAssured.given()
				.body(jsonProdutoCorreto)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
			.when()
				.post()
			.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarNameEStatus200_QuandoObterProdutoPorSku() {
		cadastraProduto1();
		RestAssured.given()
				.pathParam("sku", produtoCadastrado_1.getSku())
				.accept(ContentType.JSON)
			.when()
				.get("/{sku}")
			.then()
				.statusCode(HttpStatus.OK.value())
				.body("name", equalTo(produtoCadastrado_1.getName()));
	}

	@Test
	public void deveRetornarProdutoIsMarketable_QuandoObterProdutoPorSku() {
		cadastraProdutoExistente();
		RestAssured.given()
			.pathParam("sku", produtoJaCadastrado.getSku())
			.accept(ContentType.JSON)
		.when()
			.get("/{sku}")
		.then()
			.body("isMarketable", equalTo(produtoJaCadastrado.getIsMarketable()));
	}
	
	@Test
	public void deveRetornarStatus404_QuandoObterProdutoPorSkuInexistente() {
		RestAssured.given()
			.pathParam("sku", 99999)
			.accept(ContentType.JSON)
		.when()
			.get("/{sku}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}

	@Test
	public void deveRetornarStatus204_QuandoDeletarProdutoPorSkuExistente() {
		cadastraProdutoExistente();
		RestAssured.given()
			.pathParam("sku", produtoJaCadastrado.getSku())
			.accept(ContentType.JSON)
		.when()
			.delete("/{sku}")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void deveRetornarExcecaoProdutoExistente_QuandoCriarProdutoComSkuExistente() {
		cadastraProdutoDuplicidade();
		RestAssured.given()
			.body(jsonProdutoExistente)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
			.when()
				.post()
			.then()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.body("userMessage", equalTo("Dois produtos são considerados iguais se os seus skus forem iguais"));
	}
	
	@Test
	public void deveAltualizarNameProduto1_QuandoAtualizarProdutoComSkuExistente() {
		cadastraProduto1();
		RestAssured.given()
			.body(jsonAtualizaProdutoExistente)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("sku", produtoCadastrado_1.getSku())
			.when()
				.put("/{sku}")
			.then()
				.statusCode(HttpStatus.OK.value())
				.body("name", equalTo("Nome alterado"));
	}
	
	private void cadastraProduto1() {
		List<Warehouse> warehouses = new ArrayList<>();
		produtoCadastrado_1 = new Produto();
		produtoCadastrado_1.setSku(Long.valueOf(1));
		produtoCadastrado_1
				.setName("M·A·C Matte Lipstick Taupe - Batom 3g");
		
		  Inventory inventory = new Inventory(); 
		  warehouses.add(new Warehouse("SP",1,"ECOMMERCE")); inventory.setWarehouses(warehouses);
		  produtoCadastrado_1.setInventory(inventory);
		  
		  produtoService.salvar(produtoCadastrado_1);
		 
	}
	private void cadastraProdutoDuplicidade() {
		List<Warehouse> warehouses = new ArrayList<>();
		produtoCadastradoComDuplicidade = new Produto();
		produtoCadastradoComDuplicidade.setSku(Long.valueOf(4));
		produtoCadastradoComDuplicidade
				.setName("Wella Professionals Brilliance Protect Serum - Ampola de Tratamento 3x10ml");
		
		  Inventory inventory = new Inventory(); warehouses.add(new
		  Warehouse("SP",1,"ECOMMERCE")); inventory.setWarehouses(warehouses);
		  produtoCadastradoComDuplicidade.setInventory(inventory);
		  
		  produtoService.salvar(produtoCadastradoComDuplicidade);
		 
	}
	private void cadastraProdutoExistente() {
		 produtoJaCadastrado = new Produto();
		 produtoJaCadastrado.setSku(Long.valueOf(2));
		 produtoJaCadastrado.setName("Wella Professionals Brilliance Protect Serum - Ampola de Tratamento 3x10ml");
		 
		 Inventory inventory = new Inventory(); List<Warehouse> warehouses = new ArrayList<>(); 
		 warehouses.add(new Warehouse("SP",1,"ECOMMERCE"));
		 warehouses.add(new Warehouse("AM",3,"ECOMMERCE"));
		 inventory.setWarehouses(warehouses);
		 produtoJaCadastrado.setInventory(inventory);
		 
		 produtoService.salvar(produtoJaCadastrado);
		 
	}
}
