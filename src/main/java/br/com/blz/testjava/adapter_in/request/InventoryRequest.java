package br.com.blz.testjava.adapter_in.request;

import br.com.blz.testjava.adapter_in.util.ConstantsNames;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class InventoryRequest {

    @JsonProperty(ConstantsNames.PRODUCT_WAREHOUSES)
    private List<WareHousesRequest> wareHousesRequestList;

    @JsonCreator
    public InventoryRequest(@JsonProperty(ConstantsNames.PRODUCT_WAREHOUSES) List<WareHousesRequest> wareHousesRequestList) {
        this.wareHousesRequestList = wareHousesRequestList;
    }
}
