package br.com.blz.testjava.dtos;

import br.com.blz.testjava.enums.WarehouseType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WarehouseDTO {

    @NotNull
    private String locality;

    @NotNull
    private Integer quantity;

    @NotNull
    private WarehouseType type;
}
