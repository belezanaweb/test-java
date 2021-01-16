package br.com.blz.testjava.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class Inventory {

    private Integer quantity;
    private List<WareHouse> wareHouses;

    public Inventory(List<WareHouse> wareHouses) {
        this.wareHouses = wareHouses;
    }
}
