package br.com.blz.testjava.web.request;

import br.com.blz.testjava.core.domain.Product;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequestDTO implements Serializable {

    @NotNull(message = "Field sku could not be null.")
    private Integer sku;

    @NotBlank(message = "Field name could not be null/empty.")
    private String name;

    @NotNull(message = "Field inventory could not be null.")
    @Valid
    private InventoryRequestDTO inventory;

    public Product toDomain() {
        return Product.builder()
            .sku(sku)
            .name(name)
            .inventory(inventory.toDomain())
            .build();
    }
}
