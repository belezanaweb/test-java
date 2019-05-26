package br.com.blz.endpoints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.blz.models.InventoryEntity;
import br.com.blz.models.ProductEntity;
import br.com.blz.models.WarehouseEntity;

public class Teste {

	public static ProductEntity getClienteTeste() {

		//SETA WAREHOUSE 1
		WarehouseEntity warehouseEntity_x1 = new WarehouseEntity();
		warehouseEntity_x1.setLocality("SP");
		warehouseEntity_x1.setQuantity(12);
		warehouseEntity_x1.setType("ECOMMERCE");
		
		//SETA WAREHOUSE 2
		WarehouseEntity warehouseEntity_x2 = new WarehouseEntity();
		warehouseEntity_x2.setLocality("MOEMA");
		warehouseEntity_x2.setQuantity(3);
		warehouseEntity_x2.setType("PHYSICAL_STORE");
		
		//SETA IVENTORY 1 -> p/ WAREHOUSE 1 + WAREHOUSE 2
		InventoryEntity inventoryEntity_x1 = new InventoryEntity();
		inventoryEntity_x1.getWarehouses().add(warehouseEntity_x1);
		inventoryEntity_x1.getWarehouses().add(warehouseEntity_x2);
		
		//SETA PRODUCT
		ProductEntity productEntity = new ProductEntity();
		productEntity.setSku(new Long("43264"));
		productEntity.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
		
		//ASSOCIA IVENTORY
		productEntity.setInventory(inventoryEntity_x1);
		
	
		
		return productEntity;

	}
}
