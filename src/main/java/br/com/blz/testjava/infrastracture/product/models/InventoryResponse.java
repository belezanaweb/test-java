package br.com.blz.testjava.infrastracture.product.models;

import br.com.blz.testjava.application.product.InventoryOutput;
import br.com.blz.testjava.domain.product.Warehouse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record InventoryResponse(@JsonProperty Long quantity, @JsonProperty List<Warehouse> warehouses) {

    public static InventoryResponse from(final InventoryOutput output) {
        return new InventoryResponse(output.quantity(), output.warehouses());
    }
}
