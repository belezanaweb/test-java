package br.com.blz.testjava.controller;

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

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.service.ProductService;
import br.com.blz.testjava.util.Message;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService service;

	@PostMapping
	public ResponseEntity<Message> createProduct(@RequestBody Product product) {
		
		Product productObj = service.findProduct(product.getSku());
		
		if (productObj != null) {
			return new ResponseEntity<Message>(
					new Message("Product with same sku already exists"),
					HttpStatus.CONFLICT);
		}
		
		if (product.getIsMarketable() != null || product.getInventory().getQuantity() != null) {
			return new ResponseEntity<Message>(
					new Message("Malformed JSON"),
					HttpStatus.BAD_REQUEST);
		}
		
		service.persistProduct(product);
		
		return new ResponseEntity<Message>(
				new Message("The product has been created"),
				HttpStatus.CREATED);
		
	}

	@PutMapping("/{sku}")
	public ResponseEntity<Message> editProduct(@PathVariable("sku") Long sku, @RequestBody Product product) {
		
		if (product.getIsMarketable() != null && product.getInventory().getQuantity() != null) {
			return new ResponseEntity<Message>(
					new Message("Malformed JSON"),
					HttpStatus.BAD_REQUEST);
		}
		
		if (!service.updateProduct(sku, product)) {
			return new ResponseEntity<Message>(
					new Message("Product cannot be found"),
					HttpStatus.NOT_FOUND);
		}		
		
		return new ResponseEntity<Message>(
				new Message("The product has been updated"),
				HttpStatus.OK);
		
	}

	@GetMapping("/{sku}")
	public ResponseEntity<Product> recoverProduct(@PathVariable("sku") Long sku) {
		
		if(service.findProduct(sku) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Product productObj = service.findProduct(sku);
		return new ResponseEntity<Product>(productObj, HttpStatus.FOUND);
		
		
	}

	@DeleteMapping("/{sku}")
	public ResponseEntity<Message> deleteProduct(@PathVariable("sku") Long sku) {
		
		if (service.deleteProduct(sku) == true) {
			return new ResponseEntity<Message>(
					new Message("Product has been deleted"),
					HttpStatus.OK);
		}
		
		return new ResponseEntity<Message>(
				new Message("Product cannot be found"),
				HttpStatus.NOT_FOUND);
		
	}

}
