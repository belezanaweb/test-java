package br.com.blz.testjava.controller.request;

import lombok.Data;

@Data
public class ProductRequest {

    private Long sku;
    private String name;
    private InventoryRequest inventory;
}
