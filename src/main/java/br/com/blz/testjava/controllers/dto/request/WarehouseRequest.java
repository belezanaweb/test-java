package br.com.blz.testjava.controllers.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WarehouseRequest {

    private String locality;

    private Long quantity;

    private String type;

}
