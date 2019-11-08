package br.com.blz.testjava;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.dto.Inventory;
import br.com.blz.testjava.dto.Produto;
import br.com.blz.testjava.dto.WareHouse;

@RunWith(SpringRunner.class)


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestJavaApplication.class)
public class TestJavaApplicationTests {
	
	@Autowired
    private TestRestTemplate restTemplate;


	@Test
	public void testeInclusao() {
		
		Produto produto = buildProduto();		
		this.restTemplate.put("/v1/produtos-app/criar-produto", produto);
	    Produto body =  this.restTemplate.getForEntity("/v1/produtos-app/busca-produto/"+produto.getSku(), Produto.class).getBody();
	      
	    assert (body) != null;  
	}
	
	
	@Test
	public void testeBuscaProduto() {
		
		Produto produto = buildProduto();		
		this.restTemplate.put("/v1/produtos-app/criar-produto", produto);
		Produto body =  this.restTemplate.getForEntity("/v1/produtos-app/busca-produto/"+produto.getSku(), Produto.class).getBody();
	    assert (body).getSku().equals(produto.getSku());  
	}
	
	@Test
	public void testeDelecao() {
		
		Produto produto = buildProduto();		
		this.restTemplate.put("/v1/produtos-app/criar-produto", produto);
		this.restTemplate.delete("/v1/produtos-app/remover-produto/"+produto.getSku());
	    Produto body =  this.restTemplate.getForEntity("/v1/produtos-app/busca-produto/"+produto.getSku(), Produto.class).getBody();
	      
	    assert (body) == null;  
	}
	
	
	@Test
	public void updateProduto() {
		
		Produto produto = buildProduto();		
		this.restTemplate.put("/v1/produtos-app/criar-produto", produto);
		produto.setName("Perfume Polo Play");
		
		this.restTemplate.postForEntity("/v1/produtos-app/atualizar-produto/"+produto.getSku(), produto, Produto.class);
	    Produto body =  this.restTemplate.getForEntity("/v1/produtos-app/busca-produto/"+produto.getSku(), Produto.class).getBody();
	    
	    assert (body).getName().equals("Perfume Polo Play");
	}
	
	
	public static Produto buildProduto() {
	
		Produto produto = new  Produto();
		produto.setSku("43264");
		produto.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
		Inventory inventory = new Inventory();
		List<WareHouse>  listWareHouse = new ArrayList<WareHouse>();
		listWareHouse.add(WareHouse.builder().locality("SP").quantity(12).type("ECOMMERCE").build());
		listWareHouse.add(WareHouse.builder().locality("MOEMA").quantity(3).type("PHYSICAL_STORE").build());
		inventory.setWarehouses(listWareHouse);
		produto.setInventory(inventory);
		
		return produto;
	}

}
