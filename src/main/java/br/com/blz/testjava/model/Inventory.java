package br.com.blz.testjava.model;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Inventory {
    private Integer quantity;
    private List<Warehouse> warehouses;
}
