package br.com.blz.testjava.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.repository.entity.Product;
import br.com.blz.testjava.service.ProductService;
import br.com.blz.testjava.transform.Transform;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PostMapping("/product")
	public void create(@RequestBody ProductDto productDto) {
		Product product = Transform.toProduct(productDto);
		
		productService.create(product);
	}
	
	@PutMapping("/product/{sku}")
	public void update(@PathVariable long sku, @RequestBody ProductDto productDto) {
		Product product = Transform.toProduct(productDto);
		productService.update(product);
	}

	@DeleteMapping("/product/{sku}")
	public void delete(@PathVariable long sku) {
		productService.delete(sku);
	}

	@GetMapping("/product/{sku}")
	public ProductDto get(@PathVariable long sku) {
		Product product = productService.findById(sku);
		if (product == null) {
			return null;
		}

		return Transform.toProductDto(product);
	}
	
	@GetMapping("/products")
	public List<ProductDto> getAll() {
		List<Product> all = productService.findAll();
		
		return all.stream().map(p -> Transform.toProductDto(p)).collect(Collectors.toList());
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
}
