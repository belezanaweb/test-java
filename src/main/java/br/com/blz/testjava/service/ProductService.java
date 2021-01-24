package br.com.blz.testjava.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.entity.WarehousesType;

@Service
public class ProductService {

    private Integer SKU = (int) 43264;
    private String NAME = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";

	public Product findBySku(Integer sku) {
		
		if (sku == 43264) {
			
			List<Warehouse> warehouses = new ArrayList<Warehouse>();
			
			Warehouse warehouse = new Warehouse();
			warehouse.setLocality("SP");
			warehouse.setQuantity(12);
			warehouse.setType(WarehousesType.ECOMMERCE);
			warehouses.add(warehouse);

			warehouse = new Warehouse();
			warehouse.setLocality("MOEMA");
			warehouse.setQuantity(3);
			warehouse.setType(WarehousesType.PHYSICAL_STORE);
			warehouses.add(warehouse);
			

			Inventory inventory = new Inventory(warehouses);
			Product product = new Product(SKU, NAME, inventory);
			
			return product;
		}

		return null;

	}

}
