package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.repository.WarehouseRepository;

@Service
public class WarehouseService {

	@Autowired
	private WarehouseRepository repository;
	
	public Warehouse findOrCreate(Warehouse findWarehouse) {
		Warehouse foundedWarehouse = repository.findByLocalityAndType(findWarehouse.getLocality(), findWarehouse.getType());
		
		return foundedWarehouse == null ? repository.save(findWarehouse) : foundedWarehouse;
	}
	
}
