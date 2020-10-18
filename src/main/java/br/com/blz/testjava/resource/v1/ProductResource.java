package br.com.blz.testjava.resource.v1;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/v1/products")
public class ProductResource implements Serializable {

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
	public ResponseEntity<Product> findBySku(@PathVariable(value = "sku", required = true) Long sku) {
		Product product = this.productService.findBySku(sku);

		return ResponseEntity.ok(product);
	}

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Product entity) {
		this.productService.save(entity);
	}

	@PutMapping("/{sku}")
	public ResponseEntity<Product> update(@PathVariable(value = "sku", required = true) Long sku,  @RequestBody Product entity) {
		Product product = this.productService.updateBySku(sku, entity);

		return ResponseEntity.ok(product);
	}

	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{sku}")
	public void deleteBySku(@PathVariable(value = "sku", required = true) Long sku) {
		 this.productService.deleteBySku(sku);
	}

}
