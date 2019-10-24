
package br.com.blz.testjava.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;
import static org.springframework.http.HttpStatus.valueOf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.exception.BusinessException;
import br.com.blz.testjava.models.Product;
import br.com.blz.testjava.service.ProductService;
import br.com.blz.testjava.utils.Constants;

@RestController
@RequestMapping("/product")
@SuppressWarnings("rawtypes")
public class ProductController {

	private static Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@PostMapping("/save")
	public ResponseEntity<ProductResponse> save(@RequestBody Product product) {

		ProductResponse<Product> response;

		try {
			log.info("Received the product to save with the infos: {}", product.toString());
			productService.save(product);
			response = new ProductResponse<>(OK.value(), Constants.OPERATION_OK, product);

		} catch (BusinessException e) {

			log.error("There was a Business Exception. Exception: {}", e.getMessage(), e);
			response = new ProductResponse<>(PRECONDITION_FAILED.value(), e.getMessage(), product);
		} catch (Exception e) {

			log.error("There was a General Exception. Exception: {}", e.getMessage(), e);
			response = new ProductResponse<>(INTERNAL_SERVER_ERROR.value(), e.getMessage(), product);
		}

		return new ResponseEntity<>(response, valueOf(response.getHttpStatus()));
	}

	@PutMapping("/update/{sku}")
	public ResponseEntity<ProductResponse> update(@PathVariable Integer sku, @RequestBody Product product) {

		ProductResponse<Product> SKUResponse;

		try {
			log.info("Received the product to update with ID: {} and infos: {}", sku, product.toString());
			product.setSku(sku);
			productService.update(product);
			SKUResponse = new ProductResponse<>(OK.value(), Constants.OPERATION_OK, product);
		} catch (BusinessException e) {

			log.error("There was a Business Exception. Exception: {}", e.getMessage(), e);
			SKUResponse = new ProductResponse<>(PRECONDITION_FAILED.value(), e.getMessage(), product);
		} catch (Exception e) {

			log.error("There was a General Exception. Exception: {}", e.getMessage(), e);
			SKUResponse = new ProductResponse<>(INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}

		return new ResponseEntity<>(SKUResponse, valueOf(SKUResponse.getHttpStatus()));
	}

	@GetMapping("/search/{sku}")
	public ResponseEntity<ProductResponse> find(@PathVariable Integer sku) {

		ProductResponse<Product> productResponse;

		try {
			log.info("Searching the product with ID: {}", sku);
			Product product = this.productService.find(sku);
			productResponse = new ProductResponse<>(OK.value(), product);
		} catch (BusinessException e) {

			log.error("There was a Business Exception. Exception: {}", e.getMessage(), e);
			productResponse = new ProductResponse<>(PRECONDITION_FAILED.value(), e.getMessage());
		} catch (Exception e) {

			log.error("There was a General Exception. Exception: {}", e.getMessage(), e);
			productResponse = new ProductResponse<>(INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}

		return new ResponseEntity<>(productResponse, valueOf(productResponse.getHttpStatus()));
	}

	@DeleteMapping("/delete/{sku}")
	public ResponseEntity<ProductResponse> delete(@PathVariable Integer sku) {

		ProductResponse<Product> SKUResponse;

		try {
			log.info("Trying to delete the product with ID: {}", sku);
			productService.delete(sku);
			SKUResponse = new ProductResponse<>(OK.value(), Constants.OPERATION_OK);

		} catch (BusinessException e) {
			log.error("There was a Business Exception. Exception: {}", e.getMessage(), e);
			SKUResponse = new ProductResponse<>(PRECONDITION_FAILED.value(), e.getMessage());

		} catch (Exception e) {
			log.error("There was a General Exception. Exception: {}", e.getMessage(), e);
			SKUResponse = new ProductResponse<>(INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}

		return new ResponseEntity<>(SKUResponse, valueOf(SKUResponse.getHttpStatus()));
	}
}
