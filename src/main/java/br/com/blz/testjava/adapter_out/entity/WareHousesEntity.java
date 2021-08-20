package br.com.blz.testjava.adapter_out.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WareHousesEntity {

    private String locality;

    private Integer quantity;

    private String type;

}
