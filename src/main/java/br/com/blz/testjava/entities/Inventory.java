package br.com.blz.testjava.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.beans.Transient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory implements Serializable {

    private static final long serialVersionUID = 3411047823850018883L;

    private static final long EMPTY_WAREHOUSE = 0L;

    @Valid
    private Set<WareHouse> warehouses = new HashSet<>();

    @Transient
    public Long getQuantity() {
        return this.warehouses.isEmpty() ? EMPTY_WAREHOUSE : this.warehouses.stream().mapToLong(WareHouse::getQuantity).sum();
    }

}
