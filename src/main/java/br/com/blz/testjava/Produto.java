package br.com.blz.testjava;

import org.springframework.data.annotation.Id;
import java.util.Objects;

public class Produto {

    @Id
    private long sku;

    private String name;

    public Produto(long sku, String name) {
        this.sku = sku;
        this.name = name;
    }

    public Produto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return sku == produto.sku &&
            Objects.equals(name, produto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name);
    }

    public long getSku() {
        return sku;
    }

    public void setSku(long sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
