package br.com.blz.testjava.domain.product;

import br.com.blz.testjava.domain.Identifier;

import java.util.Objects;

public class Sku extends Identifier {
    private final Long value;

    private Sku(final Long value) {
        this.value = value;
    }

    public static Sku from(final Long sku) {
        return new Sku(sku);
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Sku that = (Sku) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
