package br.com.blz.testjava.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.beans.Transient;
import java.io.Serializable;

import static java.util.Objects.nonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product implements Serializable {

    private static final long serialVersionUID = 5807853952072308791L;

    @NotBlank(message = "SKU não pode ser nulo")
    @Max(16)
    private String sku;

    @NotBlank
    private String name;

    @Valid
    @NotNull
    private Inventory inventory;

    @Transient
    public boolean isMarketable() {
        return nonNull(this.inventory) && this.inventory.getQuantity() > 0;
    }


}
