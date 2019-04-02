package br.com.testeJava.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.testeJava.api.documents.Product;
import br.com.testeJava.api.responses.Response;
import br.com.testeJava.api.services.ProductService;

@RestController
@RequestMapping(path="/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping(path="/cadastrar")
	public ResponseEntity<Response<Product>> cadastrar(
			@RequestBody Product product,
			BindingResult result) {
		
						
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> errors.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Product>(errors));
		}	
		
		Product produtocheck = this.productService.listarPorSku(product.getSku());
		
		if(null!=produtocheck) {
			// SKU JÁ EXISTE
			List<String> errors = new ArrayList<String>();
			errors.add("Já existe produto com o SKU: " + produtocheck.getSku());
			return ResponseEntity.badRequest().body(new Response<Product>(errors));
		}
		
		return ResponseEntity.ok(new Response<Product>(this.productService.cadastrar(product)));
			
	}
	
	@PutMapping(path="/atualizar/{sku}")
	public ResponseEntity<Response<Product>> atualizar(
			@PathVariable(name ="sku") String sku, 
			@RequestBody Product product,
			BindingResult result) {
		
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> errors.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Product>(errors));
		}	
				
		Product produtocheck = this.productService.cadastrar(product);
		
		// Chama listarporSku para atualizar a quantidade e IsMarketable
		produtocheck = this.productService.listarPorSku(product.getSku());
	
		return ResponseEntity.ok(new Response<Product>(produtocheck));
				
	}

	
	@GetMapping(path="/buscarPorSku/{sku}")
	public ResponseEntity<Response<Product>> listarPorsku(@PathVariable(name ="sku") String sku){
		return ResponseEntity.ok(new Response<Product>(this.productService.listarPorSku(sku)));
	}
	
	@DeleteMapping("/remover/{sku}")
	public ResponseEntity<Response<Integer>> remover(@PathVariable(name ="sku") String sku){
		this.productService.remover(sku);
		return ResponseEntity.ok(new Response<Integer>(1));
	}
	
}
