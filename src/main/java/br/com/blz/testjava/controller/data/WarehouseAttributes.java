package br.com.blz.testjava.controller.data;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WarehouseAttributes {
    @NotNull(message = "Informe o local do armazém!")
    private String locality;

    @NotNull(message = "Informe a quantidade do produto no armazém!")
    private Long quantity;

    @NotNull(message = "Informe o tipo do armazém!")
    private String type;
}
