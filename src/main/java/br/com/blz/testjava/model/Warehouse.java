package br.com.blz.testjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Warehouse {

	private String locality;
	
	private int quantity;
	
	public Warehouse() {}
	
}
