package br.com.blz.testjava.unit.compose;

import br.com.blz.testjava.enums.WareHouseTypeEnum;
import br.com.blz.testjava.entities.WareHouse;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WareHouseCompose {

    private static final WareHouse WARE_HOUSE_ECOMMERCE = getWareHouseEcommerce();
    private static final WareHouse WARE_HOUSE_LOJA_FISICA = getWareHouseLojaFisica();

    private static WareHouseTypeEnum getRandomWareHouseTypeEnum() {
        return Utils.getRandomTrueOrFalse() ? WareHouseTypeEnum.ECOMMERCE : WareHouseTypeEnum.PHYSICAL_STORE;
    }

    public static WareHouse getWareHouseEcommerce() {
        return WareHouse.builder()
            .locality("SP")
            .quantity(12)
            .type(WareHouseTypeEnum.ECOMMERCE)
            .build();
    }

    public static WareHouse getWareHouseLojaFisica() {
        return WareHouse.builder()
            .locality("Moema")
            .quantity(3)
            .type(WareHouseTypeEnum.PHYSICAL_STORE)
            .build();
    }

    public static Set<WareHouse> getWareHousesDefault() {
        return Stream.of(WARE_HOUSE_ECOMMERCE, WARE_HOUSE_LOJA_FISICA).collect(Collectors.toSet());
    }

    public static Set<WareHouse> getWareHousesRamdom() {

        Set<WareHouse> warehouses = new HashSet<WareHouse>();

        for (int i = 0; i < 10; i++) {
            WareHouse wareHouse = WareHouse.builder()
                .locality(Utils.getPalavraAleatoria())
                .quantity(Utils.getNumeroAleatorio())
                .type(getRandomWareHouseTypeEnum())
                .build();
            warehouses.add(wareHouse);
        }

        return warehouses;
    }

}
