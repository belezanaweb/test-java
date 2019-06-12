package br.com.blz.testjava.product;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.blz.testjava.error.ErrorResponse;
import br.com.blz.testjava.error.NoProductResultException;
import br.com.blz.testjava.error.ProductSavedException;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@PostMapping("/")
	@ResponseBody
	public ResponseEntity<Object> saveProduct(@RequestBody @Valid Product product) {
		try {
			Product p = service.saveProduct(product);
			return new ResponseEntity<>(p, HttpStatus.CREATED);
		} catch (ProductSavedException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{sku}")
	@ResponseBody
	public Product getProduct(@NotNull @PathVariable Long sku) {
		return service.getBySku(sku);
	}
	
	@PutMapping("/")
	@ResponseBody
	public ResponseEntity<Object> updateProduct(@RequestBody @Valid Product product) {
		try {
			Product p = service.update(product);
			return new ResponseEntity<>(p, HttpStatus.OK);
		} catch (NoProductResultException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{sku}")
	@ResponseBody
	public ResponseEntity<Object> deleteProduct(@NotNull @PathVariable Long sku) {
		try {
			service.deleteBySku(sku);
			return new ResponseEntity<>("Product deleted.", HttpStatus.OK);
		} catch (NoProductResultException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
