package br.com.blz.testjava.model;


public enum WarehouseType {

	ECOMMERCE, PHYSICAL_STORE;
	
	
	public static WarehouseType getWarehouseType(String typePorEscrito) {
		for(WarehouseType warehouseType : WarehouseType.values()) {
			if(warehouseType.name().equals(typePorEscrito)) {
				return warehouseType;
			}
		}
		
		return null;
	}
	
}
