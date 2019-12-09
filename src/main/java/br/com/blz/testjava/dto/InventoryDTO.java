package br.com.blz.testjava.dto;

import java.util.ArrayList;

public class InventoryDTO {

	private int quantity;

	ArrayList<WarehousesDTO> warehousesDTO;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ArrayList<WarehousesDTO> getWarehousesDTO() {
		return warehousesDTO;
	}

	public void setWarehousesDTO(ArrayList<WarehousesDTO> warehousesDTO) {
		this.warehousesDTO = warehousesDTO;
	}

}
