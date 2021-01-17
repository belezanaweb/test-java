package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
public class Inventory {
    private Integer quantity;
    private List<Warehouse> warehouses;
}
