package br.com.blz.testjava.product.controller;

import static br.com.blz.testjava.util.TestUtil.parseJson;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.blz.testjava.product.model.Inventory;
import br.com.blz.testjava.product.model.Product;
import br.com.blz.testjava.product.model.Warehouse;
import br.com.blz.testjava.product.model.WarehouseType;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
public class ProductControllerTest {

	public Product defaultProduct;
	
	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private MockMvc mvc;
	
	@BeforeEach
	public void setup() {
		
		Warehouse warehouseSP = Warehouse.builder().locality("SP").quantity(12L).type(WarehouseType.ECOMMERCE).build();
		Warehouse warehouseMoema = Warehouse.builder().locality("MOEMA").quantity(3L).type(WarehouseType.PHYSICAL_STORE).build();
		ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
		warehouses.add(warehouseMoema);
		warehouses.add(warehouseSP);
		Inventory inventory = Inventory.builder().warehouses(warehouses).build();
		
		defaultProduct = Product.builder().name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g").sku(43264L).inventory(inventory).build();
	
		em.persist(defaultProduct);
	}
	
	@Test
	void shouldCreateAProduct() throws Exception {
		
		Product simpleProduct = Product.builder().name("Simple Product").sku(50L).build();
		
		mvc.perform(post("/product").content(parseJson(simpleProduct)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}
	
	@Test
	void shouldNotLetCreateTwoProducts_withSameSKU() throws Exception {
		mvc.perform(post("/product").content(parseJson(defaultProduct)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(NOT_ACCEPTABLE.value()));
	}
	
	@Test
	void shouldUpdateProductBySKU() throws Exception {
		
		defaultProduct.getInventory().getWarehouses().add(Warehouse.builder().locality("GUARULHOS").quantity(10L).type(WarehouseType.PHYSICAL_STORE).build());
		
		mvc.perform(put("/product/" + defaultProduct.getSku()).content(parseJson(defaultProduct)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
		mvc.perform(get("/product/" + defaultProduct.getSku())).andExpect(jsonPath("$.inventory.quantity", is(25)));
		
	}
	
	
	@Test
	void shouldDeleteProductBySKU() throws Exception {
		mvc.perform(delete("/product/" + defaultProduct.getSku())).andExpect(status().isAccepted());
		
		mvc.perform(get("/product/" + defaultProduct.getSku())).andExpect(jsonPath("$").doesNotExist());
	}
	

	@Test
	void productWithNoQuantityInInventoryShouldNotBeMarketable() throws Exception {
		Product notMarketableProduct = Product.builder().name("Not Marketable Product").sku(1L).build();
		
		mvc.perform(post("/product").content(parseJson(notMarketableProduct)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
		
		mvc.perform(get("/product/" + notMarketableProduct.getSku())).andExpect(jsonPath("$.isMarketable", is(false)));
	}
	
	@Test
	void productWithQuantityInInventoryShouldBeMarketable() throws Exception  {
		mvc.perform(get("/product/" + defaultProduct.getSku())).andExpect(jsonPath("$.isMarketable", is(true)));
	}
	
	@Test
	void productQuantityShouldBeTheSumOfAllWarehousesQuantity() throws Exception  {
		mvc.perform(get("/product/" + defaultProduct.getSku())).andExpect(jsonPath("$.inventory.quantity", is(15)));
	}
	
	
}
