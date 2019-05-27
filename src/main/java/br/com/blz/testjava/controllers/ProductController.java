package br.com.blz.testjava.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.blz.testjava.models.Product;
import br.com.blz.testjava.responses.Response;
import br.com.blz.testjava.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(path="/new")
	public ResponseEntity<Response<Product>> newProduct(@RequestBody Product product, BindingResult result) {
		Response<Product> response = new Response<Product>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Product productSaved = this.productService.save(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{sku}").buildAndExpand(product.getSku())
				.toUri();
		response.setData(productSaved);
		return ResponseEntity.created(location).body(response);
	}
	
	@GetMapping(path = "/{sku}")
	public ResponseEntity<Product> buscar(@PathVariable("sku") Long sku) {
		Product product = productService.findById(sku);
		Response<Product> response = new Response<Product>();
		response.setData(product);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> products = productService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}
	
	@DeleteMapping(path = "/{sku}")
	public String delete(@PathVariable("sku") Long sku) {
		productService.delete(sku);
		return "Product deleted successfully";
	}
	
	@PutMapping(path="/edit")
	public ResponseEntity<Response<Product>> putProduct(@RequestBody Product product, BindingResult result) {
		Response<Product> response = new Response<Product>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Product productSaved = this.productService.update(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{sku}").buildAndExpand(product.getSku())
				.toUri();
		response.setData(productSaved);
		return ResponseEntity.created(location).body(response);
	}
}