package br.com.blz.testjava.resource;

import java.io.Serializable;

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
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource implements Serializable {

	private static final long serialVersionUID = 5592180154760440507L;
	
	private ProductService productService;
	
	@Autowired
	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping()
	public ResponseEntity<Iterable<Product>> findAll() {
		Iterable<Product> products = this.productService.findAll();
		
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{sku}")
	public ResponseEntity<Product> findBySku(@PathVariable(value = "sku", required = true) Integer sku) {
		Product product = this.productService.findById(sku);
		
		return ResponseEntity.ok(product);
	}
	
	@PostMapping("/salvar")
	public void salvar(@RequestBody Product entity) {
		this.productService.save(entity);
	}
	
	@PutMapping("/{sku}/atualizar")
	public ResponseEntity<Product> atualizar(@PathVariable(value = "sku", required = true) Integer sku,  @RequestBody Product entity) {
		Product product = this.productService.update(sku, entity);
		
		return ResponseEntity.ok(product);
	}
	
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{sku}")
	public void delete(@PathVariable(value = "sku", required = true) Integer sku) {
		 this.productService.deleteById(sku);
	}
	
}
