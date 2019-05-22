package br.com.blz.testjava.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.Products;

@RestController
@RequestMapping("/products")
public class ProductResource {

	@Autowired
	private Products products;

	@GetMapping
	public List<String> all() {
		return Arrays.asList("Condicionador");
	}

	@PostMapping
	public Product create(@RequestBody Product produto) {
		return products.save(produto);
	}
}
