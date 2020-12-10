package br.com.blz.testjava.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
public class Warehouse extends BaseDomain{
    private String locality;
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type{
        ECOMMERCE,PHYSICAL_STORE
    }
}
