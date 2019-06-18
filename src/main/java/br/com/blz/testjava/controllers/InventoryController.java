package br.com.blz.testjava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.blz.testjava.models.Inventory;
//import br.com.blz.testjava.repositories.ProductRepository;
import br.com.blz.testjava.repositories.InventoryRepository;

@Controller
@RequestMapping(path="inventory")
public class InventoryController {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	
	//CREATE
	@PostMapping(path="/add")
	public @ResponseBody String addNewInventory(@RequestBody Inventory inventory ) {
		
		inventoryRepository.save(inventory);							
		return "Saved";
	}
	
	//UPDATE
	@PutMapping(path="/update/{id}")
	public @ResponseBody String updateProduct(@RequestBody Inventory inventory ) {
		inventoryRepository.save(inventory);			
		return "Updated";
	}
    

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Inventory> getAllInventories(){
		return inventoryRepository.findAll();
	}
    

}	
	