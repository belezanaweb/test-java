package br.com.blz.testjava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.repository.WarehouseRepository;

@Service
public class WarehouseService {
     
	@Autowired
	WarehouseRepository warehouseRepository;
	
	 @Autowired
	 Warehouse warehouse;
	 
	public void create(Warehouse warehouse) {  
		this.warehouse.setLocality(warehouse.getLocality());
		this.warehouse.setQuantity(warehouse.getQuantity());
		this.warehouse.setType(warehouse.getType());
		warehouseRepository.save(this.warehouse);
	}
	
	public List<Warehouse>list(){
		return warehouseRepository.findAll();
	}
	
	
	
	
}
