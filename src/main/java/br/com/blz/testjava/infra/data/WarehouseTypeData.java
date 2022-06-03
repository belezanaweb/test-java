package br.com.blz.testjava.infra.data;

import br.com.blz.testjava.core.domain.WarehouseType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum WarehouseTypeData {

    ECOMMERCE(WarehouseType.ECOMMERCE),
    PHYSICAL_STORE(WarehouseType.PHYSICAL_STORE),
    OTHER(WarehouseType.OTHER);

    private WarehouseType domainValue;

    public static WarehouseTypeData resolve(WarehouseType domainValue) {
        return Stream.of(values())
            .filter(v -> v.domainValue == domainValue)
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

}
