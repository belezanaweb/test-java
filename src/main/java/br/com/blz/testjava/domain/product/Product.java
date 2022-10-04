package br.com.blz.testjava.domain.product;

import br.com.blz.testjava.domain.AggregateRoot;
import br.com.blz.testjava.domain.exceptions.DomainException;
import lombok.Getter;

import java.util.List;

import static br.com.blz.testjava.domain.product.Sku.from;

@Getter
public class Product extends AggregateRoot<Sku> implements Cloneable {

    private final String name;
    private final List<Warehouse> warehouses;

    private Product(Sku sku, String name, List<Warehouse> warehouses) {
        super(sku);
        this.name = name;
        this.warehouses = warehouses;
        isValid();
    }

    public static Product newProduct(final Long sku, final String name, final List<Warehouse> warehouses) {
        return new Product(from(sku), name, warehouses);
    }

    public static Product with(long sku, String name) {
        return new Product(from(sku), name, null);
    }

    public static Product with(long sku, String name, List<Warehouse> warehouses) {
        return new Product(from(sku), name, warehouses);
    }

    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    protected void isValid() {
        if (this.getId() == null || this.getId().getValue() == null)
            throw new DomainException("'SKU' não pode ser nulo.");
        if (this.name == null) throw new DomainException("'nome' não pode ser nulo.");
        if ("".equals(this.name)) throw new DomainException("'nome' não pode ser vazio.");
    }
}
