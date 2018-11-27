package br.com.blz.testjava.gateways.http.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Builder
@Data
public class ProductDTO {

    private Long id;

    @NotEmpty(message = "Product Name can not be empty!")
    private String name;

    @NotNull(message = "SKU can not be null!")
    @Min(value = 0, message = "Sku number can not be less than zero")
    private Integer sku;

    @NotNull(message = "Inventory can not be null!")
    private InventoryDTO inventory;
}
