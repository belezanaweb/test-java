package br.com.blz.testjava.data.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class WarehouseDTO  implements Serializable {

    @NotBlank(message = "The locality cannot be empty")
    private String locality;
    @NotNull(message = "Please put a quantity for warehouses")
    @Size(min = 1)
    private Integer quantity;
    @NotBlank(message = "Please specify a type for your warehouse")
    private PointOfServiceTypeDTO type;
}
