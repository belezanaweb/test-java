package br.com.blz.testjava.domain;

import br.com.blz.testjava.dto.WarehouseDto;

import javax.persistence.*;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private String locality;

    private Integer quantity;

    private String type;

    public Warehouse() {
    }

    public Warehouse(Product product, WarehouseDto warehouseDto) {
        this.product = product;
        this.locality = warehouseDto.getLocality();
        this.quantity = warehouseDto.getQuantity();
        this.type = warehouseDto.getType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
