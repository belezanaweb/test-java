package br.com.blz.testjava;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


import br.com.blz.testjava.dao.entity.Product;
import br.com.blz.testjava.resource.ProductResource;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SpringBootBootstrapLiveTest {

	 private static final String API_ROOT = "http://localhost:8080/api/products";

	 
	 @Test
	    public void whenGetAllProducts_thenOK() {
	        final Response response = RestAssured.get(API_ROOT);
	        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	    }

//	    @Test
//	    public void whenGetProductsByTitle_thenOK() {
//	        final Product product = createRandomProduct();
//	        createProductAsUri(product);
//
//	        final Response response = RestAssured.get(API_ROOT + "/title/" + product.getTitle());
//	        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
//	        assertTrue(response.as(List.class)
//	            .size() > 0);
//	    }

/**	    @Test
	    public void whenGetCreatedProductById_thenOK() {
	        final Product product = createRandomProduct();
	        final String location = createProductAsUri(product);

	        final Response response = RestAssured.get(location);
	        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	            .get("title"));
	    }

	    @Test
	    public void whenGetNotExistProductById_thenNotFound() {
	        final Response response = RestAssured.get(API_ROOT + "/" + randomNumeric(4));
	        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	    }

	    // POST
	    @Test
	    public void whenCreateNewProduct_thenCreated() {
	        final Product product = createRandomProduct();

	        final Response response = RestAssured.given()
	            .contentType(MediaType.APPLICATION_JSON_VALUE)
	            .body(product)
	            .post(API_ROOT);
	        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
	    }

	    @Test
	    public void whenInvalidProduct_thenError() {
	        final Product product = createRandomProduct();
	        product.setAuthor(null);

	        final Response response = RestAssured.given()
	            .contentType(MediaType.APPLICATION_JSON_VALUE)
	            .body(product)
	            .post(API_ROOT);
	        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
	    }

	    @Test
	    public void whenUpdateCreatedProduct_thenUpdated() {
	        final Product product = createRandomProduct();
	        final String location = createProductAsUri(product);

	        product.setId(Long.parseLong(location.split("api/products/")[1]));
	        product.setAuthor("newAuthor");
	        Response response = RestAssured.given()
	            .contentType(MediaType.APPLICATION_JSON_VALUE)
	            .body(product)
	            .put(location);
	        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

	        response = RestAssured.get(location);
	        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	        assertEquals("newAuthor", response.jsonPath()
	            .get("author"));

	    }

	    @Test
	    public void whenDeleteCreatedProduct_thenOk() {
	        final Product product = createRandomProduct();
	        final String location = createProductAsUri(product);

	        Response response = RestAssured.delete(location);
	        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

	        response = RestAssured.get(location);
	        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	    }
	 
	 
	 
   private Product createRandomProduct() {
       Product product = new Product();
       product.setTitle(randomAlphabetic(10));
       product.setAuthor(randomAlphabetic(15));
       return product;
   }

   private String createProductAsUri(Product product) {
       Response response = RestAssured.given()
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .body(product)
         .post(API_ROOT);
       return API_ROOT + "/" + response.jsonPath().get("id");
   }**/
}
