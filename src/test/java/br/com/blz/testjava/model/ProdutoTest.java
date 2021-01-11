package br.com.blz.testjava.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ProdutoTest {

	private Long skuNumber = 7L;
	private Long skuNumber2 = 20L;
	private String prod1= "Name product one";
	private String prod2= "Name product two";
	Produto produto;
	Produto produto2;
	
	@BeforeEach
	public void setup() {
		produto = new Produto();
		produto2 = new Produto();
	}
	
	@Test
	void shouldReturnTrueWhenTheSameProduct() {	
		
		assertTrue(produto.equals(produto));
			
	}
	
	@Test
	void shouldReturnTrueWhenTheProductsPointToTheSameMemoryAddress() {
		
		produto2 = produto;
		assertTrue(produto.equals(produto2));
	}
	
	@Test
	void shouldReturnTrueWhenTheProductsHasTheSameSku() {
		
		produto.setSku(skuNumber);
		produto.setName(prod1);
		
		produto2.setSku(skuNumber);
		produto2.setName(prod2);
		
		assertTrue(produto.equals(produto2));
	}
	
	@Test
	void shouldReturnFalseWhenTheProductsHasntTheSameSku() {

		produto.setSku(skuNumber);
		produto.setName(prod1);
		
		produto2.setSku(skuNumber2);
		produto2.setName(prod2);
		
		assertFalse(produto.equals(produto2));
	}
	
	@Test
	void shouldReturnFalseWhenTheObjectIsNull() {
		
		produto.setSku(skuNumber);
		produto.setName(prod1);
		
		produto2 = null;
		
		assertFalse(produto.equals(produto2));
	}
	
	@Test
	void shouldReturnFalseWhenTheProductSkuIsNull() {
		
		produto.setSku(null);
		produto.setName(prod1);
		
		produto2.setSku(skuNumber2);
		
		assertFalse(produto.equals(produto2));
	}
	
	@Test
	void shouldReturnTrueWhenTheProductsSkuIsNull() {
		
		produto.setSku(null);
		produto.setName(prod1);
		
		produto2.setSku(null);
		
		assertTrue(produto.equals(produto2));
	}
	
	@Test
	void shouldReturnFalseWhenNotTheSameObject() {
		
		produto.setSku(skuNumber);
		produto.setName(prod1);
		
		BigDecimal bd = new BigDecimal(3);
		
		assertFalse(produto.equals(bd));
	}

}
