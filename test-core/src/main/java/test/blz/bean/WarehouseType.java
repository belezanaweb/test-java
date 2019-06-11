package test.blz.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum WarehouseType {

    ECOMMERCE("ECOMMERCE"),
    PHYSICAL_STORE("PHYSICAL_STORE");

    private String description;

    public static WarehouseType fromDescription(String description) {
        for (WarehouseType type : WarehouseType.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Description [" + description + "] not supported.");
    }
}
