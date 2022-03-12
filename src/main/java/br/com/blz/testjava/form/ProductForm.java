package br.com.blz.testjava.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ProductForm {
    @NotNull
    private Long Sku;
    @NotBlank
    private String name;
    @NotNull
    private List<WarehouseForm> warehouses;
}
