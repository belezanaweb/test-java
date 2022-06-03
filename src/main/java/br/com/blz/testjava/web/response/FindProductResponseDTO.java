package br.com.blz.testjava.web.response;

import br.com.blz.testjava.core.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindProductResponseDTO implements Serializable {

    private Integer sku;
    private String name;
    private InventoryResponseDTO inventory;
    private Boolean isMarketable;

    public FindProductResponseDTO from(Product product){
        return FindProductResponseDTO.builder()
            .sku(product.getSku())
            .name(product.getName())
            .inventory(product.getInventory().toDto())
            .isMarketable(product.getIsMarketable())
            .build();
    }
}
