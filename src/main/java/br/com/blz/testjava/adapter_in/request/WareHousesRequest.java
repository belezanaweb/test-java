package br.com.blz.testjava.adapter_in.request;

import br.com.blz.testjava.adapter_in.util.ConstantsNames;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WareHousesRequest {

    @JsonProperty(ConstantsNames.PRODUCT_LOCALITY)
    private String locality;

    @JsonProperty(ConstantsNames.PRODUCT_QUANTITY)
    private Integer quantity;

    @JsonProperty(ConstantsNames.PRODUCT_TYPE)
    private String type;

    @JsonCreator
    public WareHousesRequest(@JsonProperty(ConstantsNames.PRODUCT_LOCALITY) String locality,
                             @JsonProperty(ConstantsNames.PRODUCT_QUANTITY) Integer quantity,
                             @JsonProperty(ConstantsNames.PRODUCT_TYPE) String type) {
        this.locality = locality;
        this.quantity = quantity;
        this.type = type;
    }
}
