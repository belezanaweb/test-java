package br.com.blz.testjava.adapter_in.response;

import br.com.blz.testjava.adapter_in.util.ConstantsNames;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductResponse {

    @JsonProperty(ConstantsNames.PRODUCT_SKU)
    private Long sku;

    @JsonProperty(ConstantsNames.PRODUCT_NAME)
    private String name;

    @JsonProperty(ConstantsNames.PRODUCT_INVETORY)
    private InventoryResponse inventoryResponse;

    @JsonProperty(ConstantsNames.PRODUCT_IS_MARKETABLE)
    private Boolean isMarketable;

    @JsonCreator
    public ProductResponse(
        @JsonProperty(ConstantsNames.PRODUCT_SKU) Long sku,
        @JsonProperty(ConstantsNames.PRODUCT_NAME) String name,
        @JsonProperty(ConstantsNames.PRODUCT_INVETORY) InventoryResponse inventoryResponse,
        @JsonProperty(ConstantsNames.PRODUCT_IS_MARKETABLE) Boolean isMarketable) {
        this.sku = sku;
        this.name = name;
        this.inventoryResponse = inventoryResponse;
        this.isMarketable = isMarketable;
    }
}
