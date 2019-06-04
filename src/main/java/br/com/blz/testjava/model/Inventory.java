package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Inventory {

    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer quantity;

    private List<Warehouse> warehouses;
}
