package br.com.blz.testjava.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Product {

    @Getter
    @Setter
    private Long sku;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Inventory inventory;

    private Boolean isMarketable = Boolean.FALSE;

    public Boolean getIsMarketable() {
        return this.isMarketable = this.inventory.getWareHousesList().stream().count() > 0;
    }
}
