package br.com.blz.testjava.controller;

import br.com.blz.testjava.domain.model.Inventory;
import br.com.blz.testjava.domain.model.Product;
import br.com.blz.testjava.domain.model.Warehouse;
import br.com.blz.testjava.service.ProductService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

@WebMvcTest
public class ProductControllerTest {

  @Autowired
  private ProductController productController;

  @MockBean
  private ProductService productService;

  @BeforeEach
  public void setup() {
    standaloneSetup(productController);

    final Product productOne = createProductOne();
    final Product productTwo = createProductTwo();
    List<Product> products = Arrays.asList(productOne, productTwo);

    when(productService.findBySku(1L)).thenReturn(productOne);
    when(productService.findBySku(2L)).thenReturn(productTwo);
    when(productService.listAll()).thenReturn(products);
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
      .get("/products/{sku}", 2L)
      .then()
      .statusCode(HttpStatus.OK.value());
  }

    @Test
  void shouldReturnNotFound_whenFindProductBySku() {
    given()
      .accept(ContentType.JSON)
      .when()
      .get("/products/{sku}", 3L)
      .then()
      .statusCode(HttpStatus.NOT_FOUND.value());
  }

  @Test
  void shouldReturnSuccess_whenCreateProduct() {
    Product product = new Product();
    product.setSku(4L);
    product.setName("Prod Perfumaria x");

    List<Warehouse> warehouses = new ArrayList<>();
    warehouses.add(new Warehouse("TATUAPE", 11, "PHYSICAL_STORE"));

    Inventory inventory = new Inventory();
    inventory.setWarehouses(warehouses);
    product.setInventory(inventory);

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
  void shouldReturnSuccess_whenUpdateProduct() {
    Product product = createProductOne();
    product.setName("Nova descrição produto 1");
    given()
      .body(product)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
      .when()
      .patch("/products/{sku}", 1L)
      .then()
      .statusCode(HttpStatus.OK.value());
  }

  @Test
  void shouldReturnSuccess_whenRemoveProduct() {
    given()
      .accept(ContentType.JSON)
      .when()
      .delete("/products/{sku}", 2L)
      .then()
      .statusCode(HttpStatus.NO_CONTENT.value());
  }

  private Product createProductOne() {
    Product productOne = new Product();
    productOne.setSku(1L);
    productOne.setName("Shampoo");

    List<Warehouse> warehouses = new ArrayList<>();
    warehouses.add(new Warehouse("MOEMA", 12, "PHYSICAL_STORE"));

    Inventory inventory = new Inventory();
    inventory.setWarehouses(warehouses);
    productOne.setInventory(inventory);
    return productOne;
  }

  private Product createProductTwo() {
    Product productTwo = new Product();
    productTwo.setSku(2L);
    productTwo.setName("Sabonete");

    List<Warehouse> warehouses = new ArrayList<>();
    warehouses.add(new Warehouse("PAULISTA", 5, "PHYSICAL_STORE"));
    warehouses.add(new Warehouse("TATUAPE", 11, "PHYSICAL_STORE"));

    Inventory inventory = new Inventory();
    inventory.setWarehouses(warehouses);
    productTwo.setInventory(inventory);

    return productTwo;
  }

}
