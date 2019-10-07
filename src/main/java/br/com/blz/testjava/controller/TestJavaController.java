package br.com.blz.testjava.controller;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;
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
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.mock.ProductsMock;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;

@RestController
public class TestJavaController {

	@Autowired
	private ProductsMock productsMock;

	@GetMapping(value = "/product/{sku}")
	public ResponseEntity<Product> getProductBySku(@PathVariable(value = "sku") Long sku) {

		Optional<Product> productOptional = this.productsMock.products.stream()
				.filter(p -> p.getSku().compareTo(sku) == 0).findFirst();

		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			product.getInventory().setQuantity(
					product.getInventory().getWarehouses().stream().map(Warehouse::getQuantity).reduce(0L, Long::sum));
			product.setIsMarketable(product.getInventory().getQuantity().compareTo(0L) == 1);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}

		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings({ "static-access", "rawtypes" })
	@PostMapping(value = "/product")
	public ResponseEntity<String> createProduct(@Valid @RequestBody Product product) {
		Optional<Product> productOptional = this.productsMock.products.stream()
				.filter(p -> p.getSku().compareTo(product.getSku()) == 0).findFirst();
		if (productOptional.isPresent()) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST 		)
					.ok("Dois produtos são considerados iguais se os seus skus forem iguais");
		}
		this.productsMock.products.add(product);
		return new ResponseEntity(HttpStatus.OK).ok("Produto criado");
	}

	@SuppressWarnings("static-access")
	@PutMapping(value = "/product/{sku}")
	public ResponseEntity<Product> updateProductBySku(@PathVariable(value = "sku") Long sku,
			@Valid @RequestBody Product newProduct) {

		Optional<Product> productOptional = this.productsMock.products.stream()
				.filter(p -> p.getSku().compareTo(sku) == 0).findFirst();

		if (productOptional.isPresent()) {
			int index = this.productsMock.products.indexOf(productOptional.get());
			this.productsMock.products.remove(productOptional.get());
			this.productsMock.products.add(index, newProduct);
			return getProductBySku(sku);
		}

		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@DeleteMapping(value = "/product/{sku}")
	public ResponseEntity<Object> remomeProductBySku(@PathVariable(value = "sku") Long sku) {

		Optional<Product> productOptional = this.productsMock.products.stream()
				.filter(p -> p.getSku().compareTo(sku) == 0).findFirst();

		if (productOptional.isPresent()) {
			this.productsMock.products.remove(productOptional.get());
			return new ResponseEntity(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
