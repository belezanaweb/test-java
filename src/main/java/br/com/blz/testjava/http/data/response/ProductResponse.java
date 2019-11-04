package br.com.blz.testjava.http.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long sku;
    private String name;
    private InventoryResponse inventory;
    @JsonProperty(value = "isMarketable")
    private Boolean marketable;
}
