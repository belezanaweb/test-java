package dto;

import br.com.blz.testjava.entity.Type;

public class WarehouseDTO {

	private String locality;
	private int quantity;
	private Type type;

	public WarehouseDTO() {

	}

	public WarehouseDTO(String locality, int quantity, Type type) {
		super();
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
