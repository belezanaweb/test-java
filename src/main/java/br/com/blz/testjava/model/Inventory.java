package br.com.blz.testjava.model;

import java.util.List;

import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Inventory {

	@Transient
	private int quantity;

	private List<Warehouse> warehouses;
	
	public Inventory() {}

}
