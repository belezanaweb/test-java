package br.com.blz.testjava.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.blz.testjava.model.dto.ProductDTO;
import br.com.blz.testjava.model.view.ProductViews;
import br.com.blz.testjava.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@RequestMapping("/products")
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductService service;

	
	@PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(ProductViews.ProductRead.class)
	public ResponseEntity<ProductDTO> post(@Valid @RequestBody @JsonView(ProductViews.ProductWrite.class) ProductDTO product) {
		log.info("calling post from ProductController");
		return ResponseEntity.ok(service.saveProduct(product));
	}
	
	@PutMapping(value = "/{idProduct}",consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(ProductViews.ProductRead.class)
	public ResponseEntity<ProductDTO> put(
			@Valid @RequestBody @JsonView(ProductViews.ProductWrite.class) ProductDTO product,
			@PathVariable("idProduct") String idProduct) {
		log.info("calling put from ProductController");
		product.setSKU(idProduct);
		return ResponseEntity.ok(service.updateProduct(product));
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(ProductViews.ProductRead.class)
	public ResponseEntity<List<ProductDTO>> getAll() {
		if (this.service.getAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(service.getAll());
	}
	
	@GetMapping(value = "/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> getById(@PathVariable("idProduct") String idProduct) {
		return ResponseEntity.ok(service.getById(idProduct));
	}
	
	@DeleteMapping(value = "/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> deleteById(@PathVariable("idProduct") String idProduct) {
		this.service.deleteById(idProduct);
		return ResponseEntity.noContent().build();
	}
}
