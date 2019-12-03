package br.com.blz.testjava.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import br.com.blz.testjava.mappers.dtos.ProductDTO;
import br.com.blz.testjava.models.Product;
import br.com.blz.testjava.services.ProductService;

@RestController
@RequestMapping(path = "/products")
public class ProductResource {
	
	@Qualifier("productService")
	private ProductService productService;

	@Autowired
	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO dto) {

		Product saveProduct = productService.create(dto);
 
		return ResponseEntity.status(HttpStatus.CREATED).body(saveProduct);
	}  

	@PutMapping("/{sku}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long sku, @RequestBody ProductDTO productDTO) {
		productService.update(sku, productDTO);

		return ResponseEntity.accepted().build();
	}

	@GetMapping("/{sku}")
	public ResponseEntity<Product> searchProduct(@PathVariable Long sku) {
		Product product = productService.search(sku);

		return ResponseEntity.ok().body(product);
	}

	@DeleteMapping("/{sku}")
	public ResponseEntity<Product> deleteProduct(@PathVariable Long sku) {

		productService.destroy(sku);

		return ResponseEntity.accepted().build();
	}
}
