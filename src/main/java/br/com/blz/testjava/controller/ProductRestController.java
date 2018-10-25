package br.com.blz.testjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/belezanaweb/produtos")
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "/{sku}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Product> findProductBySku(@PathVariable Integer sku) {
		Product productFound = this.productService.findProductBySku(sku);
		return new ResponseEntity<Product>(productFound, HttpStatus.OK);
		
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Product> saveProduct(@RequestBody ProductDTO productDTO) {
		Product productSaved = this.productService.saveProduct(productDTO);
		return new ResponseEntity<Product>(productSaved, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{sku}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Product> updateProduct(@PathVariable Integer sku, @RequestBody ProductDTO productDTO) {
		Product productUpdated = this.productService.updateProduct(sku, productDTO);
		return new ResponseEntity<Product>(productUpdated, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{sku}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> deleteProduct(@PathVariable Integer sku) {
		boolean deleteProduct = this.productService.deleteProduct(sku);
		if (deleteProduct) {
			return new ResponseEntity<String>("O Produto foi excluído com Sucesso!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("ERRO: Não foi possível excluir o Produto, pois ele NÃO existe na base de dados da Beleza na Web!", HttpStatus.NOT_FOUND);
		}
		
	}
	
}
