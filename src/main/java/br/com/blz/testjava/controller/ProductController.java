package br.com.blz.testjava.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.controller.custom.CustomErrorHandler;
import br.com.blz.testjava.custom.BusinessDuplicatedException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping(path = "/api/product", produces = MediaType.APPLICATION_JSON_VALUE)
public final class ProductController extends CustomErrorHandler {

	@Autowired
	private ProductRepository repository;

	@PostMapping
	public final ResponseEntity<Object> register(@Valid @RequestBody Product product) throws Exception {
		if (repository.existsById(product.getSku()))
			throw new BusinessDuplicatedException();

		repository.save(product);

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{sku}")
	public final ResponseEntity<Object> update(@PathVariable int sku, @Valid @RequestBody Product product) {
		Optional<Product> productOptional = repository.findById(sku);

		if (!productOptional.isPresent())
			return ResponseEntity.notFound().build();

		product.setSku(sku);

		repository.save(product);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{sku}")
	public final ResponseEntity<Object> get(@PathVariable int sku) {
		Product product = repository.findById(sku).orElseThrow(NoSuchElementException::new);

		product = ProductService.updateQuantityAndMarketable(product);

		return ResponseEntity.ok(product);
	}

	@DeleteMapping("/{sku}")
	public final ResponseEntity<Object> delete(@PathVariable int sku) {
		repository.deleteById(sku);

		return ResponseEntity.noContent().build();
	}

}
