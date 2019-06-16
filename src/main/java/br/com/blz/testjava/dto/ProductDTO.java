/**
 * 
 */
package br.com.blz.testjava.dto;

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
public class ProductDTO {
	
	private Long sku;
	
	private String name;
	
	private InventoryDTO inventory;

	public Boolean getIsMarketable() {
		if(inventory != null) {
			return inventory.getQuantity() != null && inventory.getQuantity() > 0? Boolean.TRUE: Boolean.FALSE;
		}
		return null;
	}
	
	public void setIsMarketable(Boolean value) {
		
	}
	
}
