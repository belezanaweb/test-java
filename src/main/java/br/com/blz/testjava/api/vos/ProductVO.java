package br.com.blz.testjava.api.vos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {

    @ApiModelProperty(example = "43264", required = true)
    @NotNull
    Long sku;

    @ApiModelProperty(example = "Floratta Flores Secretas Desodorante Colônia 30ml", required = true)
    @NotBlank
    String name;

    @NotNull
    @Valid
    InventoryVO inventory;

}
