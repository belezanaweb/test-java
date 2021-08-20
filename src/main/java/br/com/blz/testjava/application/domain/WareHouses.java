package br.com.blz.testjava.application.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WareHouses {

    private String locality;

    private Integer quantity;

    private String type;

}
