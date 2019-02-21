package br.com.blz.testjava.controllers.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class InventoryResponse {

    private Long id;

    private List<WarehouseResponse> warehouses;

}
