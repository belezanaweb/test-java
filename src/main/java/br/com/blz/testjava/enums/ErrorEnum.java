package br.com.blz.testjava.enums;

import lombok.Getter;

public enum ErrorEnum {

    SKU_EXISTS("Two products are the same if your two are the same"),
    SKU_NOT_EXISTS("Sku does not exist at the base");

    @Getter
    private final String description;

    private ErrorEnum(String description){
        this.description = description;
    }
}
