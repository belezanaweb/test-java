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
public class ProductResponseJSON {

    private Integer sku;
    private String name;
    private Boolean marketable;
    private Inventory inventory;

}
