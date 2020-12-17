package br.com.blz.testjava.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Warehouse {

    public enum Type {
        ECOMMERCE,
        PHYSICAL_STORE;
    }

    @Id
    @GeneratedValue
    private Long id;
    private Integer quantity;
    private String locality;
    @Enumerated(EnumType.STRING)
    private Type type;


}
