package br.com.blz.testjava.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    private Integer quantity;
    private List<Warehouse> warehouses;

}
