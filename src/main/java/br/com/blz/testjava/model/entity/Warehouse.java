package br.com.blz.testjava.model.entity;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse implements Serializable {

    public enum Type {
        ECOMMERCE,
        PHYSICAL_STORE;
    }

//    @Id
//    private Long id;

    @NotNull
    private Integer quantity;
    @NotEmpty
    private String locality;
//    @Enumerated(EnumType.STRING)
    @NotNull
    private Type type;


}
