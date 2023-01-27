package br.com.blz.testjava.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.repository.dao.ProductRepository;
import br.com.blz.testjava.repository.entity.Product;
import br.com.blz.testjava.service.ProductService;
import br.com.blz.testjava.transform.Transform;

@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	private ProductService productService;
	private final ProductRepository productRepository;

	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	// Criação de produto
	@PostMapping("/product")
	public void create(@RequestBody ProductDto productDto) {
		Product product = Transform.toProduct(productDto);
		productService.create(product);
	}

	// Edição de produto
	@PutMapping("/{sku}")
	public ResponseEntity<Product> updateProduct(@PathVariable long sku, @RequestBody Product product) {
		Product existingProduct = productRepository.findById(sku);
		if (existingProduct == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		existingProduct.setName(product.getName());
		existingProduct.setInventory(product.getInventory());
		Product updatedProduct = productRepository.update(existingProduct);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	// Recuperação de produto
	@GetMapping("/product/{sku}")
	public ProductDto get(@PathVariable long sku) {
		Product product = productService.findById(sku);
		if (product == null) {
			return null;
		}
		return Transform.toProductDto(product);
	}

	// Lista de todos os produtos
	@GetMapping("/products")
	public List<ProductDto> getAll() {
		List<Product> all = productService.findAll();
		return all.stream().map(p -> Transform.toProductDto(p)).collect(Collectors.toList());
	}

	// Deleção de produto
	@DeleteMapping("/product/{sku}")
	public void delete(@PathVariable long sku) {
		productService.delete(sku);
	}





	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	
}