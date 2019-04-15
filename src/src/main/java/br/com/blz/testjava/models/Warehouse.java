package br.com.blz.testjava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @NotEmpty(message = "locality can't be empty")
    @NotNull(message = "locality is required")
    private String locality;

    @NotNull(message = "quantity is required")
    private Integer quantity;

    @NotEmpty(message = "type can't be empty")
    @NotNull(message = "type is required")
    private String type;
}
