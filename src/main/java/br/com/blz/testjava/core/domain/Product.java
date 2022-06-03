package br.com.blz.testjava.core.domain;

import br.com.blz.testjava.web.response.FindProductResponseDTO;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Builder
@Getter
@ToString
@AllArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 4425565430713016014L;

    @Id
    private Integer sku;
    private String name;
    private Inventory inventory;
    private Boolean isMarketable;

    public FindProductResponseDTO toDto(){
        return FindProductResponseDTO.builder()
            .sku(sku)
            .name(name)
            .inventory(inventory.toDto())
            .isMarketable(isMarketable(inventory.getQuantity()))
            .build();
    }

    private boolean isMarketable(Integer inventoryQuantity){
        return inventoryQuantity > 0;
    }

}
