package br.com.blz.testjava.product;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import br.com.blz.testjava.TestJavaApplication;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

@SpringBootTest(classes = TestJavaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class ProductStepDefs {

	@Autowired
	private TestRestTemplate template;

	private String sku;

	private ResponseEntity<ProductDTO> response;

	@Autowired
	private ProductService productService;
	
	@Before
	public void setUp() {
		this.productService.save(createProductDTO().parse());
	}
	
	@After
	public void setDown() {
		this.productService.delete(1L);
	}
	
	@Dado("o sku do produto {string}")
	public void o_sku_do_produto(String sku) {
		this.sku = sku;
	}

	@Quando("realizada uma consulta em que o produto é encontrado com sucesso")
	public void realizada_uma_consulta_em_que_o_produto_é_encontrado_com_sucesso() {
		this.response = template.getForEntity("/teste-java/product/" + this.sku, ProductDTO.class);
	}
	
	
	@Quando("realizada uma requisição para criar um novo produto com o sku de um produto já existente")
	public void realizada_uma_requisição_para_criar_um_novo_produto_com_o_sku_de_um_produto_já_existente() {
		this.response = template.postForEntity("/teste-java/product", createProductDTO(), ProductDTO.class);
	}
	
	@Quando("realizar uma requisição para atualizar o nome de um produto")
	public void realizar_uma_requisição_para_atualizar_o_nome_de_um_produto() {
		String uri = "/teste-java/product/{sku}";
		Map<String, String> params = new HashMap<String, String>();
	    params.put("sku", "1");
	    HttpEntity<ProductDTO> requestEntity = new HttpEntity<ProductDTO>(createProductDTO("Sabonete liquido"));
		this.response = template.exchange(uri, HttpMethod.PUT, requestEntity, ProductDTO.class, params);
	}
	

	@Então("a api devera retornar {int}")
	public void a_api_devera_retornar(int statusCode) {
		Assert.assertNotNull(response);
		Assert.assertEquals(statusCode, response.getStatusCodeValue());
		Assert.assertNotNull(response.getBody());
	}
	
	@E("a quantidade em estoque devera ser {int}")
	public void a_quantidade_em_estoque_devera_ser(long quantidade) {
		Assert.assertEquals(quantidade, response.getBody().getInventoryDTO().getQuantity().longValue());
	}

	@Então("o atributo que indica se o produto é negociavel devera ser {string}")
	public void o_atributo_que_indica_se_o_produto_é_negociavel_devera_ser(String marketable) {
		Assert.assertEquals(Boolean.parseBoolean(marketable), response.getBody().getIsMarketable());
	}
	
	@E("o novo nome deverá ser {string}")
	public void o_novo_nome_deverá_ser(String name) {
		Assert.assertEquals(name, response.getBody().getName());
	}
	

	private ProductDTO createProductDTO(String name) {
		ProductDTO productDTO = createProductDTO();
		productDTO.setName(name);
		return productDTO;
	}
	
	private ProductDTO createProductDTO() {
		WarehouseDTO warehouse1 = WarehouseDTO.builder()
				.locality("SP")
				.quantity(1L)
				.type(Type.ECOMMERCE)
				.build();

		WarehouseDTO warehouse2 = WarehouseDTO.builder()
				.locality("MOEMA")
				.quantity(1L)
				.type(Type.ECOMMERCE)
				.build();

		InventoryDTO inventory = InventoryDTO.builder()
				.warehousesDTO(Arrays.asList(warehouse1, warehouse2))
				.build();

		 return ProductDTO.builder()
				.sku(1L)
				.name("creme hidratante monange")
				.inventoryDTO(inventory)
				.build();
	}


}
