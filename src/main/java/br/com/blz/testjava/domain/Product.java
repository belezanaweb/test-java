package br.com.blz.testjava.domain;


import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Product extends BaseDomain{
    @Column(unique = true)
    @NotNull
    private Long sku;

    @NotEmpty
    private String name;

    @Transient
    private boolean marketable;

    @OneToOne
    @Cascade(CascadeType.ALL)
    @NotNull
    private Inventory inventory;

    @PostLoad
    @PostUpdate
    @PostPersist
    private void postHandlerQuantityCalculation(){
        int total = this.inventory.getWarehouses().stream().map(Warehouse::getQuantity).reduce(0, Integer::sum);
        this.inventory.setQuantity(total);
        this.setMarketable(total > 0);
    }
}
