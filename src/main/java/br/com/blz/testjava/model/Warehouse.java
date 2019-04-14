package br.com.blz.testjava.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Warehouse {

    private String locality;
    private Integer quantity;
    private TypeInventory type;
}
