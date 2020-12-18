package br.com.blz.testjava.api.vos;

import br.com.blz.testjava.model.enums.TypeWarehouseEnum;
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

    @NotNull
    String locality;

    @NotNull
    @Min(1)
    Long quantity;

    @NotNull
    TypeWarehouseEnum type;
}
