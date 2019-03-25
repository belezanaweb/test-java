package br.com.blz.testjava.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

import static java.util.Optional.ofNullable;

/**
 * Defines a Inventory domain model.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inventory {

    @JsonProperty(required = true)
    private List<Warehouse> warehouses;

    @JsonProperty
    public Integer getQuantity() {
        Integer quantity = NumberUtils.INTEGER_ZERO;

        for (Warehouse warehouse : this.getWarehouses()) {

            quantity += ofNullable(warehouse)
                .map(Warehouse::getQuantity)
                .orElse(NumberUtils.INTEGER_ZERO);
        }

        return NumberUtils.INTEGER_ZERO.equals(quantity) ? null : quantity;
    }

    public List<Warehouse> getWarehouses() {
        return this.warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
