package br.com.blz.testjava.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InventoryResponse {
    private Integer quantity;
    private List<WarehouseResponse> warehouses;
}
