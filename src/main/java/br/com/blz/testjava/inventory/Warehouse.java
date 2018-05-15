package br.com.blz.testjava.inventory;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class Warehouse implements Serializable {

    private Long id;
    private String locality;
    private Integer quantity;
    private Enum<Type> types;
}
