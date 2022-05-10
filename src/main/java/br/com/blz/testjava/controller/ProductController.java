package br.com.blz.testjava.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouses;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	List<Product> products = new ArrayList<Product>();
	
	
	@GetMapping("/{sku}")
	public Optional<Product> find(@PathVariable Long sku) {
		
			Optional<Product> matchingObject = Optional.ofNullable(products.stream().
				    filter(product -> sku.equals(product.getSku()))
				    .findAny()
				    .orElse(null));
			Warehouses[] warehouses = matchingObject.get().getInventory().getWarehouses();
			int total = 0;
			for (int i = 0; i < warehouses.length; i++) {
				total = total + warehouses[i].getQuantity();
			}
			
			matchingObject.get().getInventory().setQuantity(total);
			
			if(matchingObject.get().getInventory().getQuantity() > 0) {
				matchingObject.get().setIsMarketable(true);
			}
			
			return matchingObject;
		
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public List<Product> store(@RequestBody Product product) throws Exception {
		Optional<Product> productExist = null;
		
		Product p = new Product(product.getSku(), product.getName(), product.getInventory(),null);
		try {
			productExist = this.find(p.getSku());
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(productExist != null) {
			throw new Exception("Dois produtos são considerados iguais se os seus skus forem iguais"); 
		}
		
		products.add(p); 
		return products;
		
	}
	
	@PutMapping("/{sku}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product update(@PathVariable Long sku, @RequestBody Product p){
		Optional<Product> matchingObject = Optional.ofNullable(products.stream().
			    filter(product -> sku.equals(product.getSku()))
			    .findAny()
			    .orElse(null));
		
		matchingObject.get().setName(p.getName());
		
		return matchingObject.get();
	}
	
	@DeleteMapping("/{sku}")
	public List<Product> delete(@PathVariable Long sku) throws Exception {
		try {
			Product p = products
	                .stream()
	                .filter(product -> product.getSku().equals(sku))
	                .findFirst()
	                .get();
			products.remove(p);
		} catch (Exception e) {
			throw new Exception("Falha ao excluir"); 
		}
		
		return null;
		
	}
	
	
	
	
}
