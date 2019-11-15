package br.com.blz.testjava.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.services.impl.ProductServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class ProductResource {

//	@Autowired
//	private ProductRepository productRepo;

	@Autowired
	private ProductServiceImpl productServImpl;

	@GetMapping("/product/{sku}")
	public ResponseEntity<Product> findProductBy(@Valid @PathVariable("sku") Long sku) throws Exception {
		Product found = productServImpl.findBySku(sku);
		return ResponseEntity.status(HttpStatus.OK).body(found);
	}

	@PostMapping("product")
	public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product) throws Exception {
		Product insert = productServImpl.saveProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(insert);
	}

	@RequestMapping(value = "/product", method = RequestMethod.PUT)
	@PutMapping("product")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) {
		Product update = productServImpl.updateProduct(product);
		return ResponseEntity.status(HttpStatus.OK).body(update);
	}

	@RequestMapping(value = "/product/{sku}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct(@PathVariable("sku") Long sku) {
		// productServImpl.deleteProduct(sku);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}