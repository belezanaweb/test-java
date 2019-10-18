package br.com.blz.testjava.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductResource {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	

	@PostMapping
	public ResponseEntity<Product> create(@Valid @RequestBody Product product, HttpServletResponse response) {
		Product productSalved = productRepository.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productSalved);
	}

	@GetMapping("/{sku}")
	public ResponseEntity<Product> searchSku(@PathVariable Long sku) {
		Optional<Product> Product = productRepository.findById(sku);
		return Product.isPresent() ? ResponseEntity.ok(Product.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{sku}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long sku) {
		productRepository.deleteById(sku);
	}
	
	@PutMapping("/{sku}")
	public ResponseEntity<Product> update(@PathVariable Long sku, @Valid @RequestBody Product product) {
		Product productSalved = productService.update(sku, product);
		return ResponseEntity.ok(productSalved);
	}
}
