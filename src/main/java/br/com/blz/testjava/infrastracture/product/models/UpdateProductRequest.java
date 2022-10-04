package br.com.blz.testjava.infrastracture.product.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateProductRequest(@JsonProperty Long sku, @JsonProperty String name,
                                   @JsonProperty InventoryRequest inventory) {
}
