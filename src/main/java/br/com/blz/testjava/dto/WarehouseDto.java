package br.com.blz.testjava.dto;

import br.com.blz.testjava.enums.WarehouseType;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class WarehouseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String locality;

    @NotNull
    private Integer quantity;

    @NotNull
    private WarehouseType type;

}
