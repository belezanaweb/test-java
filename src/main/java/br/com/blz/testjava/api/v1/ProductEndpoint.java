package br.com.blz.testjava.api.v1;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;
import static org.springframework.http.HttpStatus.valueOf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.exepctions.BlzException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;
import br.com.blz.testjava.util.ResponseUtil;


/**
 * 
 * @author andrey
 * @since 2019-11-10
 */
@RestController
@RequestMapping("/api/v1/product")
@SuppressWarnings("rawtypes")
public class ProductEndpoint {
	private static Logger LOGGER = LoggerFactory.getLogger(ProductEndpoint.class);
	
	public static final String SUCCESS = "Sucesso";

	@Autowired
	private ProductService productService;

	@GetMapping("/{sku}")
	public ResponseEntity<ResponseUtil> find(@PathVariable Integer sku) {

		ResponseUtil<Product> response;

		try {
			Product product = this.productService.find(sku);
			response = new ResponseUtil<>(SUCCESS, product);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (BlzException e) {
			LOGGER.error("{}", e.getMessage(), e);
			response = new ResponseUtil<>(e.getMessage());
			
			return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			LOGGER.error("Erro Interno: {}", e.getMessage(), e);
			response = new ResponseUtil<>(e.getMessage());
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<ResponseUtil> save(@RequestBody Product product) {

		ResponseUtil<Product> response;

		try {
			productService.save(product);
			response = new ResponseUtil<>(SUCCESS, product);

			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (BlzException e) {
			LOGGER.error("{}", e.getMessage(), e);
			response = new ResponseUtil<>(e.getMessage(), product);
			
			return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			LOGGER.error("Erro Interno: {}", e.getMessage(), e);
			response = new ResponseUtil<>(e.getMessage(), product);
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{sku}")
	public ResponseEntity<ResponseUtil> update(@PathVariable Integer sku, @RequestBody Product product) {

		ResponseUtil<Product> response;

		try {
			product.setSku(sku);
			productService.update(product);
			response = new ResponseUtil<>(SUCCESS, product);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (BlzException e) {
			LOGGER.error("{}", e.getMessage(), e);
			response = new ResponseUtil<>(e.getMessage(), product);
			
			return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			LOGGER.error("Erro Interno: {}", e.getMessage(), e);
			response = new ResponseUtil<>(e.getMessage(), product);
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{sku}")
	public ResponseEntity<ResponseUtil> delete(@PathVariable Integer sku) {

		ResponseUtil<Product> response;

		try {
			productService.delete(sku);
			response = new ResponseUtil<>(SUCCESS);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (BlzException e) {
			LOGGER.error("{}", e.getMessage(), e);
			response = new ResponseUtil<>(e.getMessage());
			
			return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			LOGGER.error("Erro Interno: {}", e.getMessage(), e);
			response = new ResponseUtil<>(e.getMessage());
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}