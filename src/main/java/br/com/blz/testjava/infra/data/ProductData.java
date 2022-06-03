package br.com.blz.testjava.infra.data;

import br.com.blz.testjava.core.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "ProductData")
public class ProductData implements Serializable {

    @Id
    private Integer sku;
    private String name;
    private InventoryData inventory;
    private Boolean isMarketable;

    public static ProductData from(final Product product) {
        return ProductData.builder()
            .sku(product.getSku())
            .name(product.getName())
            .isMarketable(product.getIsMarketable())
            .inventory(InventoryData.from(product.getInventory()))
            .build();
    }

    public static ProductData from(final Product product, String sku) {
        return ProductData.builder()
            .sku(Integer.valueOf(sku))
            .name(product.getName())
            .isMarketable(product.getIsMarketable())
            .inventory(InventoryData.from(product.getInventory()))
            .build();
    }

    public Product toProduct(){
        return Product.builder()
            .sku(this.sku)
            .name(this.name)
            .inventory(this.inventory.toInventory())
            .isMarketable(this.isMarketable)
            .build();
    }

}
