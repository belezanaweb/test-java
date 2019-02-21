package br.com.blz.testjava.controllers.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WarehouseResponse {

    private Long id;

    private String locality;

    private Long quantity;

    private String type;

}
