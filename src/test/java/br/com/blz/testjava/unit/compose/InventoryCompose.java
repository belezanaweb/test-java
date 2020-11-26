package br.com.blz.testjava.unit.compose;

import br.com.blz.testjava.entities.Inventory;
import br.com.blz.testjava.entities.WareHouse;

import java.util.Set;

import static br.com.blz.testjava.unit.compose.WareHouseCompose.getWareHousesDefault;
import static br.com.blz.testjava.unit.compose.WareHouseCompose.getWareHousesRamdom;

public class InventoryCompose {

    private static final Set<WareHouse> WAREHOUSES = getWareHousesDefault();
    private static final Set<WareHouse> WARE_HOUSES_RAMDOM = getWareHousesRamdom();

    public static Inventory getInventoryDefault() {
        return Inventory.builder()
            .warehouses(WAREHOUSES)
            .build();
    }

    public static Inventory getInventoryRandom() {
        return Inventory.builder()
            .warehouses(WARE_HOUSES_RAMDOM)
            .build();
    }


}
