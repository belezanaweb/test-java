package br.com.blz.testjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.entity.Product;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@GetMapping("/{sku}")
	public ResponseEntity<Product> get(@PathVariable(value = "sku") long sku){
		
		Product product = new Product(sku, "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", "invetory");
        if(sku == 43264)
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

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
