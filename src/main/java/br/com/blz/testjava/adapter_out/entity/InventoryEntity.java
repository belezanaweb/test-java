package br.com.blz.testjava.adapter_out.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class InventoryEntity {

    private Integer quantity;

    private List<WareHousesEntity> wareHousesEntityList;

}
