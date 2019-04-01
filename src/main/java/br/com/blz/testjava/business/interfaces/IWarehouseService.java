package br.com.blz.testjava.business.interfaces;

import java.util.List;

import br.com.blz.testjava.model.Warehouse;

public interface IWarehouseService {
	
	List<Warehouse> getWarehouseBySku(Long sku);
	Warehouse createWarehouse(Warehouse warehouse);
	Warehouse updateWarehouse(Warehouse warehouse);
	void deleteWarehouseBySku(Warehouse warehouse);

}
