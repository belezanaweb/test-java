package br.com.blz.testjava.helper;

import br.com.blz.testjava.dto.InventoryDto;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.dto.WarehouseDto;

public class ProductConstants {

	public static ProductDto getLoreal() {
		ProductDto product = new ProductDto(43264, "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
		product.setInventory(new InventoryDto(new WarehouseDto("SP", 12, "ECOMMERCE"),
											new WarehouseDto("MOEMA", 3, "PHYSICAL_STORE")));
		
		return product;
	}
	
}
