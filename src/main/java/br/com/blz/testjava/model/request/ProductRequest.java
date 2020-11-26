package br.com.blz.testjava.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest implements Serializable {

    private static final long serialVersionUID = -6657524090823653264L;

    @NotBlank(message = "SKU não pode ser nulo")
    @Max(16)
    private String sku;

    @NotBlank
    private String name;

    @Valid
    @NotNull
    private InventoryRequest inventory;

}
