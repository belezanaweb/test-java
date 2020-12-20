package br.com.blz.testjava.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.springframework.data.annotation.Transient;

@Data
//@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory implements Serializable {

    //    @Id
//    private Long id;

//    @OneToMany(cascade = CascadeType.ALL)
    @Singular
    private Set<Warehouse> warehouses = new HashSet<>();

    @Transient
    public Long getQuantity() {
        return !warehouses.isEmpty() ? warehouses.stream().mapToLong(Warehouse::getQuantity).sum()
            : 0L;
    }

}
