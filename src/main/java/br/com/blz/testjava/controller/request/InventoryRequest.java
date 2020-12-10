package br.com.blz.testjava.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InventoryRequest {
    private List<WarehouseRequest> warehouses;
}
