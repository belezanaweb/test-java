package br.com.blz.testjava.mappers;

import br.com.blz.testjava.mappers.dtos.WarehouseDTO;
import br.com.blz.testjava.models.Warehouse;

public class WarehouseMapper {
	
	 public static Warehouse toModel(WarehouseDTO warehouseDTO) {
		 Warehouse warehouse = null;

		 warehouse = Warehouse.builder()
				 .warehouse_id(warehouseDTO.getWarehouse_id())
                   .locality(warehouseDTO.getLocality())
                   .quantity(warehouseDTO.getQuantity())
                   .type(warehouseDTO.getType())
                   .build();

	        return warehouse;
	    }
}