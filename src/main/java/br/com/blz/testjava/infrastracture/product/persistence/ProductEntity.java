package br.com.blz.testjava.infrastracture.product.persistence;

import br.com.blz.testjava.domain.product.Product;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductEntity {

    @EqualsAndHashCode.Include
    private long sku;
    private String name;
    private List<WarehouseEntity> warehouses;

    public static ProductEntity from(Product product) {
        return new ProductEntity(product.getId().getValue(), product.getName(), product.getWarehouses().stream().map(WarehouseEntity::from).toList());
    }

    public Product toAggregate() {
        if (warehouses == null) {
            return Product.with(sku, name);
        }
        return Product.with(sku, name, warehouses.stream().map(WarehouseEntity::toAggregate).toList());
    }
}
