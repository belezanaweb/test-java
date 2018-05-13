package br.com.blz.testjava.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.blz.testjava.exception.BusinessException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;

@RequestMapping(value = "/products")
@RestController
public class ProductResource {
	
	@Autowired
	ProductService service;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createProduct(@RequestBody Product product) {
		try {
			service.createProduct(product);
			URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{sku}")
                    .buildAndExpand(product.getSku()).toUri();
			return ResponseEntity.created(location).build();
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("The product you tried to create already exists!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/{sku}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> deleteProduct(@PathVariable String sku) {
		try {
			service.deleteProduct(sku);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/{sku}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> findProduct(@PathVariable String sku) {
		try {
			Product p = service.findProduct(sku);
			if (p!= null) {
				return ResponseEntity.status(HttpStatus.OK).body(p);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/{sku}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> updateProduct(@RequestBody Product product) {
		try {
			service.updateProduct(product);
			URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{sku}")
                    .buildAndExpand(product.getSku()).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> findProducts() {
		try {
			List<Product> products = service.findProducts();
			return ResponseEntity.status(HttpStatus.OK).body(products);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
