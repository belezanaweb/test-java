package br.com.blz.testjava.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class WarehouseDto implements Serializable {
	
	private static final long serialVersionUID = -1568894258118625716L;

	private String locality;
	
	private int quantity;
	
	public WarehouseDto() {}
	
}
