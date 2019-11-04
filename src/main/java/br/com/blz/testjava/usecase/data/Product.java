package br.com.blz.testjava.usecase.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @NotNull
    @Min(1)
    private Long sku;
    @NotNull
    @Size(min = 3)
    private String name;
    @NotNull
    private Inventory inventory;
    @NotNull
    private Boolean marketable;
}
