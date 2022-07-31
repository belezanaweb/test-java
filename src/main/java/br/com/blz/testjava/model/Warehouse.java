package br.com.blz.testjava.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Warehouse {

    public enum Type {
        ECOMMERCE,
        PHYSICAL_STORE
    }

    private String localilty;
    private Integer quantity;
    private Type type;
}
