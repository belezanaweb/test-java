package br.com.blz.testjava.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Augusto Lemes
 * @since 16/06/2019
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
	
	private List<WareHouseDTO> warehouses;
	
	public Integer getQuantity() {
		Integer sum = warehouses == null ? null : warehouses.stream().map(WareHouseDTO::getQuantity).mapToInt(Integer::intValue).sum();
		return sum;
	}
	
	public void setQuantity(Integer value) {
		
	}

}
