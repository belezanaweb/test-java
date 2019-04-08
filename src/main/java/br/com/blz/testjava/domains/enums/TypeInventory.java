package br.com.blz.testjava.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TypeInventory {

    ECOMMERCE("ECOMMERCE"), PHYSICAL_STORE("PHYSICAL_STORE");

    @Getter
    private String type;

}
