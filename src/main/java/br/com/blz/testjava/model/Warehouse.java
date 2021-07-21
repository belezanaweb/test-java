package br.com.blz.testjava.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Warehouse {
	@NotNull
	@NotEmpty
	private String locality;
	@NotNull
	@NotEmpty
	private long quantity;
	@NotNull
	@NotEmpty
	private String type;

	public Warehouse(@NotNull @NotEmpty String locality,
			@NotNull @NotEmpty long quantity,
			@NotNull @NotEmpty String type) {
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

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
