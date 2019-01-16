package br.com.blz.testjava.model.enumeration;

public enum WarehouseType {
	
    //@formatter:off
	ECOMMERCE("ECOMMERCE"),
	PHYSICAL_STORE("PHYSICAL_STORE");
    //@formatter:on
	
    private String description;

    WarehouseType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static WarehouseType parse(String description) {
        if (description == null || description.isEmpty()) {
            return null;
        }

        for (WarehouseType WarehouseType : values()) {
            if (WarehouseType.description.equals(description)) {
                return WarehouseType;
            }
        }
        return null;
    }
}