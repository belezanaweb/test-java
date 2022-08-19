package br.com.blz.testjava.entity;

import br.com.blz.testjava.dto.WarehouseDTO;
import lombok.Builder;
import lombok.Getter;

@Builder
public class WarehouseEntity {
	private String locality;
	@Getter
	private Long quantity;
	private WarehouseType type;

	public enum WarehouseType {
		ECOMMERCE, PHYSICAL_STORE;
	}
	
	public WarehouseDTO toDTO() {
		return WarehouseDTO.builder()
					.locality(this.locality)
					.quantity(this.quantity)
					.type(this.type)
					.build();
	}

}
