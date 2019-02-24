package br.com.blz.testjava.controllers.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {

    @NotNull(message = "O sku deve ser informado")
    private Long sku;

    @NotBlank(message = "O nome do produto deve ser informado")
    private String name;

    private InventoryRequest inventory;

}
