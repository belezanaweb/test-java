package br.com.blz.testjava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.blz.testjava.models.Warehouse;
import br.com.blz.testjava.repositories.WarehouseRepository;


@Controller
@RequestMapping(path="warehouse")
public class WarehouseController {
	
	@Autowired //to get bean warehouseRepository
	private WarehouseRepository warehouseRepository;
	
	//CREATE
	@PostMapping(path="/add")
	public @ResponseBody String addNewWarehouse(@RequestParam String locality, @RequestParam Integer quantity, @RequestParam String type ) {
		Warehouse warehouse = new Warehouse();
		warehouse.setLocality(locality);
		warehouse.setQuantity(quantity); 
		warehouse.setType(type);		
		warehouseRepository.save(warehouse);		
		return "Saved";
	}
	
	//READ
	@GetMapping(path="/{id}")
	public @ResponseBody Warehouse getWarehouse(@PathVariable(value = "id") Long id) throws Exception{
			return warehouseRepository.findOne(id);		
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Warehouse> getAllWarehouses(){
		return warehouseRepository.findAll();
	}

	//UPDATE
	@PutMapping(path="/update/{id}")
	public @ResponseBody String updateWarehouse(@PathVariable(value="id") Long id,   @RequestParam String locality, @RequestParam Integer quantity, @RequestParam String type ) {
		Warehouse warehouse = warehouseRepository.findOne(id);
		warehouse.setLocality(locality);
		warehouse.setQuantity(quantity); 
		warehouse.setType(type);		
		warehouseRepository.save(warehouse);		
		
		return "Updated";
	}
	
	@DeleteMapping(path="/{id}")
	public @ResponseBody String deleteWarehouse(@PathVariable(value = "id") Long id) throws Exception{
		    
			warehouseRepository.delete(warehouseRepository.findOne(id));
			
		return "Deleted";
	}
	
}
