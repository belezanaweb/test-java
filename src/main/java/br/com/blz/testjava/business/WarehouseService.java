package br.com.blz.testjava.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.business.interfaces.IWarehouseService;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.repository.WarehouseRepository;
import br.com.blz.testjava.util.Utils;

@Service
public class WarehouseService implements IWarehouseService{

	@Autowired
	private WarehouseRepository repository;

	@Override
	public List<Warehouse> getWarehouseBySku(Long sku) {
		List<Warehouse> warehouse = null;
		warehouse = repository.findByProductSku(sku);
		return warehouse;
	}

	@Override
	public Warehouse createWarehouse(Warehouse warehouse) {
		Warehouse newWarehouse = null;
		newWarehouse = repository.save(new Warehouse(Utils.verifyLocality(warehouse.getLocality()), 
				Utils.quantityVerify(warehouse.getQuantity()), warehouse.getType(), 
				warehouse.getProductSku()));
		return newWarehouse;
	}

	@Override
	public void deleteWarehouseBySku(Warehouse warehouse) {
		repository.delete(warehouse);
	}

	@Override
	public Warehouse updateWarehouse(Warehouse warehouse) {
		Warehouse editedWarehouse = null;
		editedWarehouse = repository.save(new Warehouse(Utils.verifyLocality(warehouse.getLocality()), 
				Utils.quantityVerify(warehouse.getQuantity()), warehouse.getType(), 
				warehouse.getProductSku()));
		return editedWarehouse;

	}

}