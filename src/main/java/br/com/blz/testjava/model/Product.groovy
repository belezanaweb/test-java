package br.com.blz.testjava.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Document(collection = "product")
class Product {

    @Id
    @NotNull
    @Min(value = 1l)
    Long sku;

    @NotNull
    @Size(min = 3,max = 600)
    String name;

    @Valid
    Inventory inventory;

    Boolean isMarketable;


    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Product product = (Product) o

        if (sku != product.sku) return false

        return true
    }

    int hashCode() {
        return (sku != null ? sku.hashCode() : 0)
    }
}
