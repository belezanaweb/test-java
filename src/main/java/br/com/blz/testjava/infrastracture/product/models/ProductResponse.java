package br.com.blz.testjava.infrastracture.product.models;

import br.com.blz.testjava.application.product.retrieve.ProductOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponse(@JsonProperty Long sku, @JsonProperty String name,
                              @JsonProperty InventoryResponse inventory, @JsonProperty Boolean isMarketable) {

    public static ProductResponse from(final ProductOutput productOutput) {
        return new ProductResponse(productOutput.sku(), productOutput.name(), InventoryResponse.from(productOutput.inventory()), productOutput.isMarketable());
    }
}
