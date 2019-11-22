package br.com.belezaNaWeb.javaTest.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.belezaNaWeb.javaTest.domain.Product;
import br.com.belezaNaWeb.javaTest.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product")
@Api(value = "product")
public class ProductController {

	Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@GetMapping()
	@ResponseBody
	@ApiOperation(value = "Return a product from database by sku code")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "The user doesn't exist"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public ResponseEntity<?> getProductBySku(@ApiParam("sku") @RequestParam("sku") String sku) {
		logger.info("Try to Find Product by sku : {}", sku);
		Product product = null;
		try {
			product = productService.findProductBySku(Long.valueOf(sku));
			logger.info("Founded Product : {}", product.getName() + "(" + product.getSku() + ")");
		} catch (Exception e) {
			logger.error("Error on find Product by sku: {}", sku);
			logger.error("Error: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, e.getMessage());
		}
		return product == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(product);
	}

	@PostMapping
	@ApiOperation(value = "Insert a product on database")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "The user doesn't exist"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public ResponseEntity<?> postProduct(@ApiParam("product") @RequestBody Product product) {
		logger.info("Try to Insert Product : {}", product.getName() + " (" + product.getSku() + ")");
		try {
			productService.createProduct(product);
			logger.info("Inserted Product : {}", product.getName() + "(" + product.getSku() + ")");
		} catch (Exception e) {
			logger.error("Error on insert Product : {}", product.getName() + " (" + product.getSku() + ")");
			logger.error("Error: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}

	@PutMapping
	@ApiOperation(value = "Alterar a product on database")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "The user doesn't exist"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public ResponseEntity<Product> putProduct(@ApiParam("product") @RequestBody Product product) {
		logger.info("Try to Update Product : {}", product.getName() + " (" + product.getSku() + ")");
		boolean productUpdated;
		try {
			productUpdated = productService.updateProduct(product);
			if(productUpdated) {
				logger.info("Updated Product : {}", product);
			}
		} catch (Exception e) {
			logger.error("Error on update Product : {}", product);
			logger.error("Error: {}", e.getMessage());
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, e.getMessage());
		}
		return !productUpdated ? ResponseEntity.noContent().build() : ResponseEntity.ok(product);
	}

	@DeleteMapping
	@ApiOperation(value = "Delete a product on database")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "The user doesn't exist"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public ResponseEntity<Void> deleteProduct(@RequestParam("sku") Long sku) {
		logger.info("Try to Delete Product with sku : {}", sku);
		try {
			productService.deleteProduct(sku);
		} catch (Exception e) {
			logger.error("Error on update Product : {}", sku);
			logger.error("Error: {}", e.getMessage());
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, e.getMessage());
		}
		logger.info("Deleted Product with sku : {}", sku);
		return ResponseEntity.noContent().build();
	}

}
