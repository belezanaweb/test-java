package br.com.blz.testjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.exceptions.ProductAlreadyExistsException;
import br.com.blz.testjava.model.Message;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;
import br.com.blz.testjava.util.ResponseDefault;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/products")
@Api(tags = {"Products Resource"})
@SwaggerDefinition(tags = {
    @Tag(name = "/products", description = "Operations about products")
})
public class ProductController {
	
	@Autowired
	private ProductService productService;

	/**
	 * 
	 * @param product
	 * @return
	 * @throws ProductAlreadyExistsException
	 */
	@PostMapping
	@RequestMapping("/")
	@ApiOperation(value = "Create a product",
    notes = "Product must be passed in a json format: {\r\n" + 
    		"    \"sku\": 43264,\r\n" + 
    		"    \"name\": \"L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g\",\r\n" + 
    		"    \"inventory\": {\r\n" + 
    		"        \"quantity\": 15,\r\n" + 
    		"        \"warehouses\": [\r\n" + 
    		"            {\r\n" + 
    		"                \"locality\": \"SP\",\r\n" + 
    		"                \"quantity\": 12,\r\n" + 
    		"                \"type\": \"ECOMMERCE\"\r\n" + 
    		"            },\r\n" + 
    		"            {\r\n" + 
    		"                \"locality\": \"MOEMA\",\r\n" + 
    		"                \"quantity\": 3,\r\n" + 
    		"                \"type\": \"PHYSICAL_STORE\"\r\n" + 
    		"            }\r\n" + 
    		"        ]\r\n" + 
    		"    },\r\n" + 
    		"    \"isMarketable\": true\r\n" + 
    		"}",
    response = ResponseDefault.class,
    responseContainer = "List")
	public ResponseDefault<Message> createProduct(@RequestBody Product product) throws ProductAlreadyExistsException{
		
		return productService.createProduct(product);
	}
	
	@PutMapping
	@RequestMapping("/alter/{sku}")
	public ResponseDefault<Message> alterProduct(@PathVariable Long sku,@RequestBody Product product){
		
		
		return productService.alterProduct(sku, product);
	}
	
	@GetMapping
	@RequestMapping("/{sku}")
	@ApiOperation(value = "Get a product by sku")
	public ResponseDefault<Product> getProduct(@PathVariable Long sku){
		
		return productService.findProduct(sku);
	}
	
	@DeleteMapping
	@RequestMapping("/delete/{sku}")
	@ApiOperation(value = "delete a product by sku")
	public ResponseDefault<Message> deleteProduct(@PathVariable Long sku){
		
		return productService.deleteProduct(sku);
	}
}
