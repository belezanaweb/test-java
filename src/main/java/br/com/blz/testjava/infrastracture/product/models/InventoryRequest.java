package br.com.blz.testjava.infrastracture.product.models;

import br.com.blz.testjava.domain.product.Warehouse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record InventoryRequest(@JsonProperty List<Warehouse> warehouses) {

    public static InventoryRequest with(final List<Warehouse> warehouses) {
        return new InventoryRequest(warehouses);
    }
}
