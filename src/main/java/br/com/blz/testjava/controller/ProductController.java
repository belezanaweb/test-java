
package br.com.blz.testjava.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;
import static org.springframework.http.HttpStatus.valueOf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.BusinessException;
import br.com.blz.testjava.service.ProductService;

@RestController
@SuppressWarnings("rawtypes")
public class ProductController {

	private static final String MSG_SUCESSO = "Operação realizada com sucesso.";

	@Autowired
	private ProductService productService;

	@PostMapping("/v1/product")
	public ResponseEntity<ProductResponse> save(@RequestBody Product product) {

		ProductResponse<Product> response;

		try {
			productService.save(product);
			response = new ProductResponse<>(OK.value(), MSG_SUCESSO, product);

		} catch (BusinessException e) {
			response = new ProductResponse<>(PRECONDITION_FAILED.value(), e.getMessage(), product);
		} catch (Exception e) {
			response = new ProductResponse<>(INTERNAL_SERVER_ERROR.value(), e.getMessage(), product);
		}

		return new ResponseEntity<>(response, valueOf(response.getHttpStatus()));
	}

	@PutMapping("/v1/product/{sku}")
	public ResponseEntity<ProductResponse> update(@PathVariable Integer sku, @RequestBody Product product) {

		ProductResponse<Product> SKUResponse;

		try {
			product.setSku(sku);
			productService.update(product);
			SKUResponse = new ProductResponse<>(OK.value(), MSG_SUCESSO, product);
		} catch (BusinessException e) {
			SKUResponse = new ProductResponse<>(PRECONDITION_FAILED.value(), e.getMessage(), product);
		} catch (Exception e) {
			SKUResponse = new ProductResponse<>(INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}

		return new ResponseEntity<>(SKUResponse, valueOf(SKUResponse.getHttpStatus()));
	}

	@GetMapping("/v1/product/{sku}")
	public ResponseEntity<ProductResponse> find(@PathVariable Integer sku) {

		ProductResponse<Product> productResponse;

		try {
			Product product = this.productService.find(sku);
			productResponse = new ProductResponse<>(OK.value(), product);
		} catch (BusinessException e) {
			productResponse = new ProductResponse<>(PRECONDITION_FAILED.value(), e.getMessage());
		} catch (Exception e) {
			productResponse = new ProductResponse<>(INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}

		return new ResponseEntity<>(productResponse, valueOf(productResponse.getHttpStatus()));
	}

	@DeleteMapping("/v1/product/{sku}")
	public ResponseEntity<ProductResponse> delete(@PathVariable Integer sku) {

		ProductResponse<Product> SKUResponse;

		try {
			productService.delete(sku);
			SKUResponse = new ProductResponse<>(OK.value(), MSG_SUCESSO);
		} catch (BusinessException e) {
			SKUResponse = new ProductResponse<>(PRECONDITION_FAILED.value(), e.getMessage());
		} catch (Exception e) {
			SKUResponse = new ProductResponse<>(INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}

		return new ResponseEntity<>(SKUResponse, valueOf(SKUResponse.getHttpStatus()));
	}
}
