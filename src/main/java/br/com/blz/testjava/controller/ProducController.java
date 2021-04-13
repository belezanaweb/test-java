package br.com.blz.testjava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.blz.testjava.service.InventoryService;
import br.com.blz.testjava.service.ProductService;
import dto.ProductDTO;

@RestController
@RequestMapping("/inventories")
public class ProducController {

    @Autowired
    private InventoryService serviceInventory;
    
	@Autowired
	private ProductService serviceProduct;

	@RequestMapping
	public List<ProductDTO> listAll() {
		return serviceProduct.listAll();
	}

	@GetMapping("/{sku}")
	public ProductDTO getProduct(@PathVariable("sku") Long sku) {
		return serviceProduct.getProduct(sku);
	}

	@PostMapping
	public ProductDTO addProduct(@RequestBody ProductDTO productTo) {
		return serviceProduct.addProduct(productTo);
	}

	@PutMapping("/{sku}")
	public ProductDTO updateProduct(@PathVariable("sku") Long sku, @RequestBody ProductDTO productTO) {
		return serviceProduct.updateProduct(sku, productTO);
	}

	@DeleteMapping("/{sku}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("sku") Long sku) {
		serviceProduct.deleteProduct(sku);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
