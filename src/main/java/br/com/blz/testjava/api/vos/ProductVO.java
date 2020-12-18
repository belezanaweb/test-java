package br.com.blz.testjava.api.vos;

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

    @NotNull
    Long sku;

    @NotBlank
    String name;

    @NotNull
    @Valid
    InventoryVO inventory;

}
