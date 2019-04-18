package br.com.blz.testjava.model;

import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Collection<Warehouse> warehouses;

    public int getQuantity() {
        if (warehouses == null) {
            return 0;
        }
        return warehouses.stream().mapToInt(w -> w.getQuantity()).sum();
    }

}

