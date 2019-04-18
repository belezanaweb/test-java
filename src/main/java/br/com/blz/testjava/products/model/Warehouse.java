package br.com.blz.testjava.products.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer quantity;
    private String locality;
    private WarehouseType type;


}

