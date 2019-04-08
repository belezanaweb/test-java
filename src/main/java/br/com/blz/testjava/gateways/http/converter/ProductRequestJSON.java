package br.com.blz.testjava.gateways.http.converter;

import br.com.blz.testjava.domains.Inventory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductRequestJSON {

    @JsonProperty("sku")
    private Integer sku;
    @JsonProperty("name")
    private String name;
    @JsonProperty("inventory")
    private Inventory inventory;

}
