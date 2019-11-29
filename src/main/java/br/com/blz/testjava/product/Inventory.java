package br.com.blz.testjava.product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Inventory {
	
	private Long quantity;
	private List<Warehouse> warehouses;
	
	public long getWarehousesInventoryQuantity() {
		return this.warehouses.stream().mapToLong(Warehouse::getQuantity).sum();
	}
}
