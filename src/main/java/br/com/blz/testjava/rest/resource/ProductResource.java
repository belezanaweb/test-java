package br.com.blz.testjava.rest.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.blz.testjava.infra.NotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.rest.dto.ProductDTO;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductResource {

	@Autowired
	private ProductService service;

	@RequestMapping(value = "/{sku}", method = RequestMethod.GET)
	public ResponseEntity<?> getProduct(@PathVariable("sku") Integer sku) {
		try {
			return ResponseEntity.ok(new ProductDTO(service.getProduct(sku)));

		} catch (NotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "/{sku}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct(@PathVariable("sku") Integer sku) {
		try {
			service.delete(sku);
			return ResponseEntity.ok().build();

		} catch (NotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insertProduct(@Valid @RequestBody ProductDTO productDTO) {
		Product product = new Product(productDTO.getSku(), productDTO.getName(), getInventory(productDTO));

		try {
			service.inserirProduto(product);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{sku}")
					.buildAndExpand(product.getSku()).toUri();

			return ResponseEntity.created(location).build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDTO productDTO) {
		Product product = new Product(productDTO.getSku(), productDTO.getName(), getInventory(productDTO));
		try {
			service.update(product);
			return ResponseEntity.ok().build();

		} catch (NotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
	}

	private List<Warehouse> getInventory(ProductDTO productDTO) {
		return productDTO.getInventory().getWarehouses().stream().map((w) -> {
			return new Warehouse(w.getLocality(), w.getQuantity(), w.getType());
		}).collect(Collectors.toList());
	}
}
