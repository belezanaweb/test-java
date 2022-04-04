package br.com.blz.testjava.api.v1.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.blz.testjava.api.v1.model.ProductDTO;
import br.com.blz.testjava.service.IProductService;

@Controller
@RequestMapping(path = "/v1/product")
public class ProductController {
	@Autowired
	private IProductService productService;
	
	@PostMapping()
	public ResponseEntity<ProductDTO> create(@RequestBody final ProductDTO product) {
		return new ResponseEntity<>(this.productService.create(product), HttpStatus.CREATED);
	}

	@PutMapping(path = "/{sku}")
	public ResponseEntity<ProductDTO> update(@PathVariable final Long sku, @RequestBody final ProductDTO product) {
		return new ResponseEntity<>(this.productService.update(sku, product), HttpStatus.OK);
	}
	
	@GetMapping(path = "/{sku}")
	public ResponseEntity<ProductDTO> findOne(@PathVariable final Long sku) {
		return new ResponseEntity<>(this.productService.findOne(sku), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{sku}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable final Long sku) {
		this.productService.delete(sku);
	}
}
