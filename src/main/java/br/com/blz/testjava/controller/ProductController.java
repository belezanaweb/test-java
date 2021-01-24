package br.com.blz.testjava.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@GetMapping("/{sku}")
    public String get() {
        return "@GetMapping";
    }
	
	@PostMapping
    public String create() {
        return "@PostMapping";
    }
	
	@PutMapping("/{sku}")
    public String update() {
        return "@PutMapping";
    }
	
	@DeleteMapping("/{sku}")
    public String delete() {
        return "@DeleteMapping";
    }
	
}
