package test.blz.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductUpdateRequest {

    private Long sku;

    private String name;

    private Inventory inventory;

    @JsonProperty("isMarketable")
    private boolean marketable;

    public ProductVO convertToVO () {
        return ProductVO.builder()
                .sku(this.getSku())
                .name(this.getName())
                .inventory(getInventoryQuantity())
                .build();
    }

    private Inventory getInventoryQuantity () {
        final Inventory inventory = this.inventory;
        Integer inventoryQuantity = 0;
        if (inventory != null && !inventory.getWarehouses().isEmpty()) {
            for (Warehouse warehouse : inventory.getWarehouses()) {
                inventoryQuantity += warehouse.getQuantity();
            }
        }
        inventory.setQuantity(inventoryQuantity);
        return inventory;
    }

}
