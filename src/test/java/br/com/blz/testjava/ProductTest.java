package br.com.blz.testjava;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.models.Inventory;
import br.com.blz.testjava.models.Product;
import br.com.blz.testjava.models.Warehouse;
import br.com.blz.testjava.utils.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest extends AbstractTest{
		
	@Override
	@Before
	public void setUp() {
      super.setUp();
	}
	
	@Test
	public void saveProduct() throws Exception {
	   String uri = "/api/v1/product/save";
	   
	   Inventory inventory = new Inventory();
	   ArrayList<Warehouse> warehouse = new ArrayList();
	   warehouse.add(new Warehouse("SP", 10, "ECOMMERCE"));
	   inventory.setWarehouses(warehouse);
	   Product product = new Product(100, "Product Name", inventory);
	   String inputJson = super.mapToJson(product);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(201, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   assertEquals(content, Constants.OPERATION_OK);
	}
	
	@Test
	public void searchProduct() throws Exception {
	   String uri = "/api/v1/product/search/100";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   Product productlist = super.mapFromJson(content, Product.class);
	   assertTrue(productlist != null);
	}


    
}
