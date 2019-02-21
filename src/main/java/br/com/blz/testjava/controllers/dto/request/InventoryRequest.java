package br.com.blz.testjava.controllers.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class InventoryRequest {

    private List<WarehouseRequest> warehouses;

}
