package br.com.blz.testjava.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.blz.testjava.entity.WarehouseEntity;
import br.com.blz.testjava.entity.WarehouseEntity.WarehouseType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WarehouseDTO {

	@NotBlank(message = "Locality coud not be null or blank")
	private String locality;
	
	@NotNull(message = "Quantity coud not be null")
	private Long quantity;

	@NotNull(message = "Type coud not be null or blank")
	private WarehouseType type;

	public WarehouseEntity toEntity() {
		return WarehouseEntity.builder().locality(this.locality).quantity(this.quantity).type(this.type).build();
	}
}
