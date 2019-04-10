package br.com.blz.testjava.controller;

import java.util.List;

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

import br.com.blz.testjava.model.ProductTO;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;

	@RequestMapping
	public List<ProductTO> listAll() {
		return service.listAll();
	}

	@GetMapping("/{sku}")
	public ProductTO getProduct(@PathVariable("sku") int sku) {
		return service.getProduct(sku);
	}

	@PostMapping
	public ProductTO addProduct(@RequestBody ProductTO productTo) {
		return service.addProduct(productTo);
	}

	@PutMapping("/{sku}")
	public ProductTO updateProduct(@PathVariable("sku") int sku, @RequestBody ProductTO productTO) {
		return service.updateProduct(sku, productTO);
	}

	@DeleteMapping("/{sku}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("sku") int sku) {
		service.deleteProduct(sku);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
