package br.com.blz.testjava.api.vos;

import br.com.blz.testjava.model.enums.TypeWarehouseEnum;
import io.swagger.annotations.ApiModelProperty;
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
public class WarehouseVO {

    @ApiModelProperty(example = "MOEMA", required = true)
    @NotNull
    String locality;

    @ApiModelProperty(example = "10", required = true)
    @NotNull
    @Min(1)
    Long quantity;

    @ApiModelProperty(example = "PHYSICAL_STORE", required = true)
    @NotNull
    TypeWarehouseEnum type;
}
