package br.com.blz.testjava.enums;

import java.util.Objects;
import java.util.Optional;

import static java.util.Arrays.stream;

public enum WarehouseType {
    ECOMMERCE("ECOMMERCE"), PHYSICAL_STORE("PHYSICAL_STORE");

    WarehouseType(String name) {

    }

    public String getName() {
        return this.name();
    }


    public static Optional<WarehouseType> fromCode(String name) {
        return stream(values())
            .filter(state -> Objects.equals(state.getName(), name))
            .findFirst();
    }
}
