package br.com.blz.testjava.adapter_in.request;

import br.com.blz.testjava.adapter_in.util.ConstantsNames;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class ProductRequest {

    @JsonProperty(ConstantsNames.PRODUCT_SKU)
    @NotNull
    private Long sku;

    @JsonProperty(ConstantsNames.PRODUCT_NAME)
    @NotEmpty
    private String name;

    @JsonProperty(ConstantsNames.PRODUCT_INVETORY)
    private InventoryRequest inventoryRequest;

    @JsonCreator
    public ProductRequest(@NotNull  @JsonProperty(ConstantsNames.PRODUCT_SKU) Long sku,
                          @NotEmpty @JsonProperty(ConstantsNames.PRODUCT_NAME) String name,
                          @JsonProperty(ConstantsNames.PRODUCT_INVETORY) InventoryRequest inventoryRequest) {
        this.sku = sku;
        this.name = name;
        this.inventoryRequest = inventoryRequest;
    }
}
