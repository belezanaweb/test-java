package br.com.blz.testjava.model.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductTypeEnum {

    ECOMMERCE(1, "ECOMMERCE"),
    PHYSICAL_STORE(2, "PHYSICAL_STORE");

    private int cod;
    private String description;

}
