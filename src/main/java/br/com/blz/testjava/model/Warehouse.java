package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotNull
    private String locality;
    @NotNull
    private int quantity;
    @NotNull
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("warehouses")
    private Inventory inventory;
}
