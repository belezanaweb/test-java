package br.com.blz.testjava.product;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WarehouseDTO {
	
	@NotNull
	private String locality;
	@NotNull
    private Long quantity;
	@NotNull
    private Type type;
    
    public static WarehouseDTO from(Warehouse warehouse) {

    	return WarehouseDTO.builder()
    			.locality(warehouse.getLocality())
    			.quantity(warehouse.getQuantity())
    			.type(warehouse.getType())
    			.build();
    }

	public Warehouse parse() {

		return Warehouse.builder()
				.locality(this.locality)
				.quantity(this.quantity)
				.type(this.type)
				.build();
	}
    

}
