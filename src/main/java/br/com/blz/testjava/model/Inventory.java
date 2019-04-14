package br.com.blz.testjava.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Inventory {

    private Integer quantity;
    private List<Warehouse> warehouses;
}
