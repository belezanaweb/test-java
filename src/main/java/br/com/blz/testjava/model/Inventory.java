package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Inventory {

    private Long id;

    @JsonIgnore
    private Integer quantity;

    private List<Warehouse> warehouses;
}
