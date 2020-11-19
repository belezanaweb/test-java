package br.com.blz.testjava.model.entities;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inventory {

    private List<Warehouse> warehouses;

}
