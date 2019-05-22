package br.com.blz.testjava.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.services.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value="/v1/products", produces={ MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes={ MediaType.APPLICATION_JSON_UTF8_VALUE })
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class ProductEndpoints {
	private final ProductService productService;

	/*
	 * - Direcionamento via verbos HTTP numa só URI de produtos.
	 * - ResponseEntity Generics para poder retornar qualquer tipo do Service, 
	 * possibilita mais flexibilidade para construção de retornos condizentes
	*/
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Product product){
		return productService.create(product);
	}
	
	@GetMapping("/{sku}")
	public ResponseEntity<?> showBySku(@PathVariable("sku") Long sku){
		return productService.getBySku(sku);
	}
	
	@DeleteMapping("/{sku}")
	public ResponseEntity<?> deleteBySku(@PathVariable("sku") Long sku){
		return productService.deleteBySku(sku);
	}
	
	/*
	 - Como nâo compreendi muito bem se era necessário SKU como PathVariable. Fiz o controle via RequestBody mesmo. 
	*/
	@PutMapping
	public ResponseEntity<?> updateBySku(@RequestBody Product product){
		return productService.updateBySku(product);
	}
}
