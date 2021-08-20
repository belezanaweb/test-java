package br.com.blz.testjava.adapter_in.response;

import br.com.blz.testjava.adapter_in.util.ConstantsNames;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class InventoryResponse {

    @JsonProperty(ConstantsNames.PRODUCT_QUANTITY)
    private Integer quantity;

    @JsonProperty(ConstantsNames.PRODUCT_WAREHOUSES)
    private List<WareHousesResponse> wareHousesResponseList;

    @JsonCreator
    public InventoryResponse(
        @JsonProperty(ConstantsNames.PRODUCT_QUANTITY) Integer quantity,
        @JsonProperty(ConstantsNames.PRODUCT_WAREHOUSES) List<WareHousesResponse> wareHousesResponseList) {
        this.quantity = quantity;
        this.wareHousesResponseList = wareHousesResponseList;
    }
}
