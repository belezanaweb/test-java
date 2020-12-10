package br.com.blz.testjava.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseResponse {
    private String locality;
    private Integer quantity;
    private Type type;

    public enum Type{
        ECOMMERCE,PHYSICAL_STORE
    }
}
