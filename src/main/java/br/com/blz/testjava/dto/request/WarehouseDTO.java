package br.com.blz.testjava.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Validated
public class WarehouseDTO {

    @NotNull(message = "{validator.warehouse.locality.notnull}")
    @Size(min=2,max=128, message = "{validator.warehouse.locality.minmax}")
    @JsonProperty("locality")
    private String locality;

    @NotNull(message = "{validator.warehouse.quantity.notnull}")
    @JsonProperty("quantity")
    private Integer quantity;

    @NotNull(message = "{validator.warehouse.type.notnull}")
    @JsonProperty("type")
    private TypeInventoryDTO type;

}
