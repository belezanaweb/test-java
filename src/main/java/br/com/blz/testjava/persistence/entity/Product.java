package br.com.blz.testjava.persistence.entity;

import br.com.blz.testjava.controller.resources.ProductRequest;
import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Document
public class Product {

    @Id
    private UUID id;
    private Long sku;
    private String name;
    private List<Warehouse> warehouses;

    public Product() {}

    public Product(UUID id, ProductRequest request) {
        this.id = id;
        this.sku = request.getSku();
        this.name = request.getName();
        this.warehouses = request.getInventory().getWarehouse().stream().map(Warehouse::new).collect(toList());
    }


    public UUID getId() {
        return id;
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equal(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + id +
            ", sku=" + sku +
            ", name='" + name + '\'' +
            ", warehouses=" + warehouses +
            '}';
    }
}



