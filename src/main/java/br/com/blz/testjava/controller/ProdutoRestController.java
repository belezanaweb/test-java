package br.com.blz.testjava.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.model.ErrorMessage;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProdutoRestController {

	@Autowired
	private ProductService service;

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Product p, UriComponentsBuilder ucBuilder) {
		System.out.println("Create Product SKU " + p.getSku());

		try {
			service.create(p);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/products/{sku}").buildAndExpand(p.getSku()).toUri());
			return new ResponseEntity<>(headers, HttpStatus.CREATED);
		} catch (ProductAlreadyExistsException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(new ErrorMessage(e.getMessage()),HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	@GetMapping
	public ResponseEntity<Collection<Product>> getAll() {
		Collection<Product> products = service.getAll();
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/{sku}")
	public ResponseEntity<Product> get(@PathVariable("sku") long sku) {
		System.out.println("Get Product with sku " + sku);
		Product p = service.get(sku);
		if (p == null) {
			System.out.println("Product with sku " + sku + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@PutMapping("/{sku}")
	public ResponseEntity<Product> update(@PathVariable("sku") long sku, @RequestBody Product p) {
		System.out.println("Update Product SKU" + sku);

		Product productFromDB = service.get(sku);

		if (productFromDB == null) {
			System.out.println("Product with SKU " + sku + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		p.setSku(sku);
		service.update(p);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@DeleteMapping("/{sku}")
	public ResponseEntity<Product> delete(@PathVariable("sku") long sku) {
		System.out.println("Get and Delete Product with sku " + sku);

		Product p = service.get(sku);
		if (p == null) {
			System.out.println("Unable to delete. Product with sku " + sku + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		service.delete(sku);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
