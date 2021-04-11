package br.com.blz.testjava.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
@Embeddable
public class Warehouse{
	@NotNull
	private String locality;
	@NotNull
	private Integer quantity;
	@NotNull
	private String type;
	
	public Warehouse() {
	}
	
	public Warehouse(String locality, Integer quantity,String type){
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}
	
	

}
