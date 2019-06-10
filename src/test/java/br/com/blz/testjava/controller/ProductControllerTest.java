package br.com.blz.testjava.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.interfaces.IProductService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductControllerTest {
	
	@Mock
	private IProductService service;

	@Before
	public void setUp() throws JsonProcessingException, IOException {	 
	    Mockito.when(service.reader(product().getSku()))
	      .thenReturn(product());
	}
	
	@Test
	public void findProduct() throws JsonProcessingException, IOException {
		
		Product product = service.reader(Long.valueOf("43264"));
		
	    assertThat(product.getInventory()).isEqualTo(inventory());
	}

	private Product product() throws JsonProcessingException, IOException {
		
		return Product.builder()
					.sku(Long.valueOf("43264"))
					.name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")
					.isMarketable(true)
					.inventory(this.inventory())
				.build();
	}
	
	private Inventory inventory() throws JsonProcessingException, IOException {
		
		return Inventory.builder()
					.id(Long.valueOf("1"))
					.quantity(0)
					.warehouses(this.warehouses())
				.build();
	}
	
	private List<Warehouse> warehouses() throws JsonProcessingException, IOException {
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		
		warehouses.add(Warehouse.builder()
				.id(Long.valueOf("1"))
				.locality("SP")
				.quantity(12)
				.type("ECOMMERCE")
			.build()
		);
		
		warehouses.add(Warehouse.builder()
				.id(Long.valueOf("1"))
				.locality("SP")
				.quantity(12)
				.type("ECOMMERCE")
			.build()
		);
		
		return warehouses;
	}
}
