package br.com.blz.testjava.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseDTO {

    private String locality;
    private int quantity;
    private String type;

}
