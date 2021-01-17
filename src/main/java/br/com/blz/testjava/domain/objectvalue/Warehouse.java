package br.com.blz.testjava.domain.objectvalue;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
public class Warehouse {

    @NotBlank
    private String locality;

    @Min(1)
    private Integer quantity;

    @NotNull
    private WarehousesType type;
}
