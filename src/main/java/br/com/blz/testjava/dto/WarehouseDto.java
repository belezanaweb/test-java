package br.com.blz.testjava.dto;

import br.com.blz.testjava.business.domain.WarehouseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class WarehouseDto {
	
	private String locality;
	private int quantity;
	private WarehouseType type;
}
