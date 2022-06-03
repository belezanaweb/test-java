package br.com.blz.testjava.web.request;

import br.com.blz.testjava.core.domain.Inventory;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequestDTO {

    @NotNull(message = "Field warehouses could not be null.")
    @Valid
    private List<WarehouseDTO> warehouses;

    public Inventory toDomain() {
        return Inventory.builder()
            .warehouses(warehouses.stream().map(
                WarehouseDTO::toDomain
                ).collect(Collectors.toList()))
            .build();
    }

}
