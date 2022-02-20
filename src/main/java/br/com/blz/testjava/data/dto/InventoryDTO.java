package br.com.blz.testjava.data.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class InventoryDTO {

    @NotNull(message = "Please put a quantity for inventories")
    @Size(min = 1)
    private Integer quantity;
    @NotNull(message = "Warehouses cannot be null ")
    private List<WarehouseDTO> warehouses;


}
