package br.com.blz.testjava.controller.data;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryAttributes {
    @NotNull(message = "Necessário preencher o campo referente ao armazém!")
    private List<WarehouseAttributes> warehouses;
}
