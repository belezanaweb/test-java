package br.com.blz.testjava.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class InventoryDTO implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1825618059324937078L;
	private Long quantity;
	private List<WarehouseDTO> warehouses = new ArrayList<>();
}
