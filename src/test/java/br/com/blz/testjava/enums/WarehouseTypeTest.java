package br.com.blz.testjava.enums;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WarehouseTypeTest {

    @Test
    void idsUniques() {
        var values = WarehouseType.values();
        assertEquals(values.length, Arrays.stream(values).map(WarehouseType::getId).distinct().count());
    }

}
