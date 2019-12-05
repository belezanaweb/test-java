package br.com.blz.testjava.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.dto.ProductDTO;
import br.com.blz.testjava.model.dto.ValidationExceptionDTO;
import br.com.blz.testjava.model.form.ProductForm;
import br.com.blz.testjava.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	
	/**
	 * Lists all products
	 * 
	 * @return
	 */
	@GetMapping
	public List<ProductDTO> index() {
		List<Product> products = repository.findAll();
		return products.stream().map(ProductDTO::new).collect(Collectors.toList());
	}
	
	/**
	 * Retrieve a product by SKU
	 * 
	 * @param sku
	 * @return
	 */
	@GetMapping("/{sku}")
	public ResponseEntity<ProductDTO> show(@PathVariable Long sku) {
		Product product = repository.findBySku(sku);
		if(product == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(new ProductDTO(product));
	}

	/**
	 * Store a new Product
	 * 
	 * @param form
	 * @param uriBuilder
	 * @return
	 */
	@PostMapping
	public ResponseEntity<ProductDTO> store(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder) {
		Product product = repository.save(form.convert());
		URI uri = uriBuilder.path("/product/{sku}").buildAndExpand(product.getSku()).toUri();
		return ResponseEntity.created(uri).body(new ProductDTO(product));
	}
	
	/**
	 * Delete product by SKU
	 * 
	 * @param sku
	 * @return
	 */
	@DeleteMapping("/{sku}")
	public ResponseEntity<?> delete(@PathVariable Long sku) {
		repository.deleteBySku(sku);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Update product with given SKU
	 * 
	 * @param sku
	 * @param form
	 * @return
	 * @throws ProductAlreadyExistsException
	 */
	@PutMapping("/{sku}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long sku, @RequestBody @Valid ProductForm form) throws ProductAlreadyExistsException {
		repository.deleteBySku(sku);
		Product product = repository.save(form.convert());
		
		return ResponseEntity.ok(new ProductDTO(product));
	}
}
