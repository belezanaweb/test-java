package br.com.blz.testjava.controller.request;

import br.com.blz.testjava.entity.WareHouse;
import lombok.Data;

import java.util.List;

@Data
public class InventoryRequest {

    private List<WareHouse> wareHouses;
}
