package br.com.blz.testjava.controller.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WarehouseRequest {
    @NotEmpty
    private String locality;

    @NotNull
    private Integer quantity;

    @NotNull
    private Type type;

    public enum Type {
        ECOMMERCE, PHYSICAL_STORE
    }
}
