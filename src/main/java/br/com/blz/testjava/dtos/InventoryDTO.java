package br.com.blz.testjava.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryDTO {

    @Valid
    @NotNull
    private List<WarehouseDTO> warehouses;

    public int getQuantity() {
        return warehouses.stream()
            .mapToInt(WarehouseDTO::getQuantity)
            .sum();
    }
}
