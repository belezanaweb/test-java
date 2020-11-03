package br.com.blz.testjava.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import br.com.blz.testjava.api.controller.ProductController;
import br.com.blz.testjava.application.ProductService;
import br.com.blz.testjava.domain.model.Inventory;
import br.com.blz.testjava.domain.model.Product;
import br.com.blz.testjava.domain.model.Warehouse;
import io.restassured.http.ContentType;

@WebMvcTest
public class ProductControllerTest {

	@Autowired
	private ProductController productController;
	
	@MockBean
	private ProductService productService;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.productController);
		
		Product productShampooLoreal = new Product();
		Inventory inventory = new Inventory();
		List<Warehouse> warehouses = new ArrayList<>();
		Warehouse warehouse = new Warehouse();
		
		warehouse.setId(1l);
		warehouse.setLocality("Moema");
		warehouse.setQuantity(12);
		warehouse.setType("Ecommerce");
		
		warehouses.add(warehouse);
		
		inventory.setId(1L);
		inventory.setWarehouses(warehouses);
		
		productShampooLoreal.setSku(1L);
		productShampooLoreal.setName("Shampoo Loreal");
		productShampooLoreal.setInventory(inventory);
		
		Product newProduct = new Product();
		newProduct.setSku(2L);
		newProduct.setName("Shampoo Boticario");
		newProduct.setInventory(inventory);
		
		Product productToUpdate = new Product();
		productToUpdate.setSku(3L);
		productToUpdate.setName("Shampoo Loreal");
		productToUpdate.setInventory(inventory);
		
		List<Product> productList = new ArrayList<>();
		productList.add(productShampooLoreal);
		productList.add(newProduct);
		productList.add(productToUpdate);
		
		when(this.productService.findBySku(1L)).thenReturn(productShampooLoreal);
		when(this.productService.findBySku(3L)).thenReturn(productToUpdate);
		when(this.productService.save(newProduct)).thenReturn(productShampooLoreal);
		when(this.productService.findAll()).thenReturn(productList);
	}
	
	@Test
	void shouldReturnSuccess_whenFindAllProducts() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/products")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void shouldReturnSuccess_whenFindProductBySku() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/products/{sku}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void shouldReturnSuccess_whenCreateProduct() {
		Product product = new Product();
		product.setSku(4L);
		product.setName("Espuma de Barbear Natura");
		product.setInventory(new Inventory());
		given()
			.body(product)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post("/products")
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	void shouldReturnSuccess_whenUpdateProductBySku() {
		Product product = new Product();
		product.setSku(1L);
		product.setName("Espuma de Barbear Boticario");
		product.setInventory(new Inventory());
		given()
			.body(product)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/products/{sku}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void shouldReturnSuccess_whenRemoveProductBySku() {
		given()
			.accept(ContentType.JSON)
		.when()
			.delete("/products/{sku}", 1L)
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
}
