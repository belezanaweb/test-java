package br.com.blz.testjava.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InventoryDTO {
    private int quantity;

    @NotEmpty
    private List<WarehouseDTO> warehouses;
}
