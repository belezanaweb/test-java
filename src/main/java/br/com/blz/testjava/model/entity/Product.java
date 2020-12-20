package br.com.blz.testjava.model.entity;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

@Data
//@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "sku")
public class Product implements Serializable {

//    @Id
//    private Long id;

    @NotNull
    private Long sku;
    @NotNull
    private String name;
    //    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    private Inventory inventory;

    @Transient
    public Boolean getIsMarketable() {
        return inventory != null && inventory.getQuantity() > 0;

    }


}

