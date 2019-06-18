package br.com.blz.testjava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.blz.testjava.models.Product;
//import br.com.blz.testjava.repositories.ProductRepository;
import br.com.blz.testjava.repositories.ProductRepository;

@Controller
@RequestMapping(path="product")
public class ProductController {
	
 
	
	@Autowired //to get bean warehouseRepository
	private ProductRepository productRepository;
	
	//CREATE
	@PostMapping(path="/add")
	public @ResponseBody String addNewProduct(@RequestBody Product product ) throws Exception {
		
		if(product.getSku() !=null && productRepository.exists(product.getSku())) 
		     throw new 	Exception("There already exists a product with this sku "+product.getSku()  );
	    
		if( productRepository.save(product)!=null) {
			return "Saved";			
		}else {
			return "Not Saved";
		}
	
	}
    
	//READ
	@GetMapping(path="/{id}")
	public @ResponseBody Product getProduct(@PathVariable(value = "id") Long id) throws Exception{
			return productRepository.findOne(id);		
	}

	//UPDATE
	@PutMapping(path="/update/{id}")
	public @ResponseBody String updateProduct(@RequestBody Product product ) {
		productRepository.save(product);
					
		return "Updated";
	}
    

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	//DELETE
	@DeleteMapping(path="/{sku}")
	public @ResponseBody String deleteWarehouse(@PathVariable(value = "sku") Long sku) throws Exception{
		    
		productRepository.delete(productRepository.findOne(sku));			
		return "Deleted";
	}
	
}	
