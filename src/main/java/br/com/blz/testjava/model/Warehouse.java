package br.com.blz.testjava.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.blz.testjava.util.WarehouseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
	
	@NotBlank
	private String locality;
	@NotNull
	private Long quantity;
	@NotNull
	private WarehouseType type;
	
}
