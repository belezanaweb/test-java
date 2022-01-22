package br.com.blz.testjava.data.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.data.dto.ProductDTO;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@GetMapping("/")
	public String index() {
		return "retorna todos os produtos";
	}
	
	@GetMapping("/{sku}")
	public String get(@PathVariable("sku") String sku) {
		return "retorna o produto " + sku;
	}
	
	@PostMapping("/") 
	public String post(@RequestBody ProductDTO prd){
		return prd.toString();
		
	}

	

}
