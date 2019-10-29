package br.com.blz.testjava.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.entity.ResponseGeneric;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.BusinessException;
import br.com.blz.testjava.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class ProductResource {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Product product) {
		
		ResponseGeneric response = new ResponseGeneric();

		try {
			
			log.info("Product to save: {}", product.toString());
			Product productSaved = productService.save(product);

			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(productSaved);
			
		} catch (BusinessException e) {

			log.error("A Business Exception was catched. Exception: {}", e.getMessage(), e);
			response.setMessage(e.getMessage());
			return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(response);
		} catch (Exception e) {

			response.setMessage(e.getMessage());
			log.error("A General Exception was catched. Exception: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(response);
		}

	}
	
	
	@PutMapping("/update/{sku}")
	public ResponseEntity<?> update(@PathVariable Integer sku, @RequestBody Product product) {


		ResponseGeneric response = new ResponseGeneric();
		
		try {
			
			log.info("Product to Update: {} and infos: {}", sku, product.toString());
			product.setSku(sku);
			Product productUpdated = productService.update(product);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(productUpdated);
			
		} catch (BusinessException e) {

			log.error("A Business Exception was catched. Exception: {}", e.getMessage(), e);
			response.setMessage(e.getMessage());
			return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(response);
		} catch (Exception e) {

			response.setMessage(e.getMessage());
			log.error("A General Exception was catched. Exception: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(response);
		}

	}
	

	@GetMapping("/search/{sku}")
	public ResponseEntity<?> find(@PathVariable Integer sku) {

		ResponseGeneric response = new ResponseGeneric();

		try {
			
			log.info("Searching the product with ID: {}", sku);
			Product product = this.productService.find(sku);
			
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(product);
			
		} catch (BusinessException e) {

			log.error("A Business Exception was catched. Exception: {}", e.getMessage(), e);
			response.setMessage(e.getMessage());
			return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(response);
		} catch (Exception e) {

			response.setMessage(e.getMessage());
			log.error("A General Exception was catched. Exception: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(response);
		}
		
	}

	@DeleteMapping("/delete/{sku}")
	public ResponseEntity<?> delete(@PathVariable Integer sku) {

		ResponseGeneric response = new ResponseGeneric();

		try {
			log.info("Trying to delete the product with ID: {}", sku);
			
			productService.delete(sku);
			
			response.setMessage("Success");
			
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);

		} catch (BusinessException e) {

			log.error("A Business Exception was catched. Exception: {}", e.getMessage(), e);
			response.setMessage(e.getMessage());
			return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(response);
		} catch (Exception e) {

			response.setMessage(e.getMessage());
			log.error("A General Exception was catched. Exception: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(response);
		}

	}
	
}
