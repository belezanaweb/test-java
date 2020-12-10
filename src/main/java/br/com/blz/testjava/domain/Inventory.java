package br.com.blz.testjava.domain;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.List;

@Data
@Entity
public class Inventory extends BaseDomain {
    @Transient
    private Integer quantity;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Warehouse> warehouses;
}
