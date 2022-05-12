package br.com.blz.testjava.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class InventoryResponse {

    private int quantity;
    private List<WareHouse> warehouses;

}
