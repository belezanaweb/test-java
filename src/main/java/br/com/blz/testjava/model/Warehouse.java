package br.com.blz.testjava.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Warehouse {
	@NotEmpty(message = "Warehouse's locality can't be empty")
	private String locality;
    
	@NotNull(message = "Warehouse's quantity can't be null")
    private Long quantity;
    
    @NotEmpty(message = "Warehouse's type can't be empty")
    private String type;
    
    

	public Warehouse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Warehouse(@NotEmpty(message = "Warehouse's locality can't be empty") String locality,
			@NotNull(message = "Warehouse's quantity can't be null") Long quantity,
			@NotEmpty(message = "Warehouse's type can't be empty") String type) {
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

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
    
    
}
