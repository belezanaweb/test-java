package br.com.blz.testjava.controllers.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class WarehouseRequest {

    @NotBlank
    private String locality;

    @NotNull
    private Long quantity;

    @NotBlank
    private String type;

}
