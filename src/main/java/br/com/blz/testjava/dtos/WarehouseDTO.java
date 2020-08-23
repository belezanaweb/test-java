package br.com.blz.testjava.dtos;

import br.com.blz.testjava.enums.WarehouseType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WarehouseDTO {

    @NotNull
    private String locality;

    @NonNull
    private int quantity;

    @NotNull
    private WarehouseType type;
}
