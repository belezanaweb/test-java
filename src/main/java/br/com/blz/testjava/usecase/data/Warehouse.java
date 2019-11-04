package br.com.blz.testjava.usecase.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    @NotNull
    private String locality; //Deveria ser um Enum?

    @NotNull(message = "quantity is required")
    @Min(value = 0, message = "quantity must be a positive number")
    private Integer quantity;
    @NotNull(message = "warehouse type is required")
    private WarehouseType type;
}
