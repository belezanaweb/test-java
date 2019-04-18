package br.com.blz.testjava.products.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(of = "sku")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long sku;
    private String name;
    private Inventory inventory;
    private boolean marketable;

    @JsonProperty("isMarketable")
    public boolean getMarketable() {
        if (inventory == null) {
            return false;
        }
        return inventory.getQuantity() > 0;
    }


}


