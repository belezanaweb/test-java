package br.com.blz.testjava;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import br.com.blz.testjava.controller.ProductController;
import br.com.blz.testjava.model.Product;



@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductControllerTest {
	
	@Autowired
	private ProductController p;
	
	private long sku = 43264;
	private String name = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";
	Product product = new Product(sku,name,null,null);
	
	@Test
	public void storeProduct() throws Throwable {
		long sku = 43264;
		String name = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";
		
		Product productTest = new Product(sku,name,null,null);
		assertEquals(product.getSku(), p.store(productTest).get(0).getSku());
		assertEquals(product.getName(), p.store(productTest).get(0).getName());
	}
	
	@Test
	public void findProduct() throws Throwable {
		long sku = 43264;
		String name = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";
		
		Product productTest = new Product(sku,name,null,null);
		assertEquals(product.getSku(), p.find(productTest.getSku()).get().getSku());
		
	}
	
	@Test
	public void deleteProduct() throws Throwable {
		long sku = 43264;
		String name = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";
		
		Product productTest = new Product(sku,name,null,null);
		assertEquals(null, p.delete(productTest.getSku()));
	}
	
	@Test
	public void updateProduct() {
		long sku = 43264;
		String name = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";
		Product productTest = new Product(sku,name,null,null);
		productTest.setName("test");
		assertEquals("test", p.update(sku, productTest).getName());
	}
	
	

}
