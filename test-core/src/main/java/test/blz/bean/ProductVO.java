package test.blz.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Transient;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductVO implements Serializable {

    private Long sku;

    private String name;

    private Inventory inventory;

    private boolean marketable;

    @Transient
    public boolean isMarketable () {
        if (inventory != null && inventory.getQuantity() > 0) {
            return true;
        }
        return false;
    }
}
