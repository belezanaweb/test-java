package br.com.blz.testjava.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class InventoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer quantity;

    @NotEmpty
    private List<WarehouseDto> warehouses;

}
