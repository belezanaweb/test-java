package br.com.blz.testjava.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WarehouseDTO {
	
	private String locality;
    private Long quantity;
    private Type type;
    
    public static WarehouseDTO from(Warehouse warehouse) {

    	return WarehouseDTO.builder()
    			.locality(warehouse.getLocality())
    			.quantity(warehouse.getQuantity())
    			.type(warehouse.getType())
    			.build();
    }

}
