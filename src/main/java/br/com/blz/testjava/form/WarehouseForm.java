package br.com.blz.testjava.form;

import br.com.blz.testjava.enums.WarehouseType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Valid
public class WarehouseForm {
    @NotBlank
    private String locality;
    @Min(0)
    private Long quantity;
    @NotNull
    private WarehouseType type;
}
