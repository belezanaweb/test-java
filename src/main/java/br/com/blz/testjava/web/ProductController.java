package br.com.blz.testjava.web;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.data.Product;
import br.com.blz.testjava.data.ProductRepository;
import br.com.blz.testjava.exceptions.ProductNotFoundException;

@RestController
public class ProductController {

	private final ProductModelAssembler assembler;
	private final ProductRepository repository;

	public ProductController(ProductModelAssembler assembler, ProductRepository repository) {
		super();
		this.assembler = assembler;
		this.repository = repository;
	}

	@GetMapping("/products/{sku}")
	EntityModel<Product> one(@PathVariable Long sku) {

		Product product = repository.findBySku(sku)
				.orElseThrow(() -> new ProductNotFoundException(sku));

		return assembler.toModel(product);
	}

	@GetMapping("/products")
	CollectionModel<EntityModel<Product>> all() {

		List<EntityModel<Product>> products = repository.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
	}

	@PostMapping("/products")
	ResponseEntity<EntityModel<Product>> newOrder(@RequestBody Product product) {

		repository.save(product);

		return ResponseEntity
				.created(linkTo(methodOn(ProductController.class).one(product.getSku())).toUri())
				.body(assembler.toModel(product));
	}

	@PutMapping("/products/{sku}")
	ResponseEntity<?> replaceProduct(@PathVariable Long sku, @RequestBody Product newProduct) {

		repository.update(sku, newProduct);
		
		Product updatedProduct = repository.update(sku, newProduct);

		EntityModel<Product> entityModel = assembler.toModel(updatedProduct);

		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);

	}

	@DeleteMapping("/products/{sku}")
	ResponseEntity<?> delete(@PathVariable Long sku) {

		repository.deleteBySku(sku);

		return ResponseEntity.noContent().build();

	}
}
