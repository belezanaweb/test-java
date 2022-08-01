package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Inventory {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer quantity;
    private List<Warehouse> warehouses;
}
