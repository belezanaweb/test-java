package br.com.blz.testjava.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.service.SkuService;

@RestController
@RequestMapping(value = "/api/v1/sku/")
public class SkuResource {
	
	@Autowired
	private SkuService skuService; 
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> teste(){
		return ResponseEntity.ok().body("Teste");
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<Product>> findSku() {
		return ResponseEntity.ok().body(this.skuService.findAll());
	}
	
	@RequestMapping(value="/{sku}", method=RequestMethod.GET)
	public ResponseEntity<Product> findSku(@PathVariable Integer sku) {
		try {
			return ResponseEntity.ok().body(this.skuService.findBySku(sku));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = {"application/json", "application/xml"})
	public ResponseEntity<String> createSku(@RequestBody Product product) {
		
		try {			
			this.skuService.createSku(product);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(product.getSku()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="{sku}", method = RequestMethod.PUT, consumes = {"application/json", "application/xml"})
	public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable Integer sku) {
		try {
			this.skuService.updateProduct(sku, product);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="{sku}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete (@PathVariable Integer sku) {
		try {
			this.skuService.deleteProduct(sku);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.noContent().build();
	}
}
