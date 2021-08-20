package br.com.blz.testjava.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
public class Inventory {

    @Setter
    private Integer quantity = 0;

    @Getter
    @Setter
    private List<WareHouses> wareHousesList = new ArrayList<>();

    public Integer getQuantity() {
        return this.wareHousesList.stream()
            .mapToInt(WareHouses::getQuantity)
            .sum();
    }
}
