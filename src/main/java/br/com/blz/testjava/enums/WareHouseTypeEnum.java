package br.com.blz.testjava.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum WareHouseTypeEnum {

    ECOMMERCE,
    PHYSICAL_STORE;

    private static class Holder {
        static Map<String, WareHouseTypeEnum> MAP = Stream.of(ECOMMERCE, PHYSICAL_STORE)
            .collect(toMap(WareHouseTypeEnum::name, identity()));
    }

    public static WareHouseTypeEnum getEnum(String enumName) {
        return Optional
            .ofNullable(Holder.MAP.get(enumName))
            .orElseThrow(() -> new IllegalStateException(String.format("Unsupported WareHouseTypeEnum %s.", enumName)));
    }

    public static boolean isEcommerce(String enumName) {
        return ECOMMERCE.name().equals(enumName);
    }

    public static boolean isPhysicalStore(String enumName) {
        return PHYSICAL_STORE.name().equals(enumName);
    }


}
