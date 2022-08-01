package br.com.blz.testjava.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class Warehouse {

    public enum Type {
        ECOMMERCE,
        PHYSICAL_STORE
    }

    @NotNull
    private String locality;

    @NotNull
    private Integer quantity;

    @NotNull
    private Type type;
}
