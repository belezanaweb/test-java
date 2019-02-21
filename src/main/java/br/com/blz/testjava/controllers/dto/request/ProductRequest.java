package br.com.blz.testjava.controllers.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {

    private Long sku;

    @NotBlank(message = "O nome do produto deve ser informado")
    private String name;

    private InventoryRequest inventory;

}
