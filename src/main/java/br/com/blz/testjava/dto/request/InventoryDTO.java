package br.com.blz.testjava.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class InventoryDTO {

    @JsonProperty("quantity")
    private Integer quantity;

    @NotNull(message = "{validator.warehouses.notnull}")
    @Valid
    @JsonProperty("warehouses")
    private List<WarehouseDTO> warehouses;
    private Boolean marketable;
}
