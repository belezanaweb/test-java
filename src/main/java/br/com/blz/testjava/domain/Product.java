package br.com.blz.testjava.domain;

import br.com.blz.testjava.dto.ProductDto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    private Long sku;
    private String name;

    public Product() {
    }

    public Product(ProductDto productDto) {
        this.sku = productDto.getSku();
        this.name = productDto.getName();
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
}
