package br.com.blz.testjava.controller.data;

import lombok.*;

import javax.validation.constraints.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductAttributes {
    @NotNull(message = "Favor informar o sku do produto!")
    private Long sku;

    @NotNull(message = "Favor informar o nome do produto!")
    private String name;

    @NotNull(message = "Necessário preencher o campo referente ao inventário!")
    private InventoryAttributes inventory;
}
