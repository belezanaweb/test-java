package br.com.blz.testjava.model.dto;

import br.com.blz.testjava.model.entity.Warehouse.Type;
import java.util.Set;
import lombok.Data;

@Data
public class ProductDto {

    @Data
    public static class Warehouse {
        private Integer quantity;
        private String locality;
        private Type type;
    }

    @Data
    public static class Inventory {
        private Set<Warehouse> warehouses;
    }

    private Long sku;
    private String name;
    private Inventory inventory;
}
