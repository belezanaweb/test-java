package br.com.blz.testjava.product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Inventory {
	
	private Long quantity;
	private List<Warehouse> warehouses;
}
