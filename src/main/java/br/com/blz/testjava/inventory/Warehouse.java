package br.com.blz.testjava.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Warehouse implements Serializable {

    @Id
    private Long id;
    @Column
    private String locality;
    @Column
    private Integer quantity;
    @Column
    private Enum<Type> types;
}
