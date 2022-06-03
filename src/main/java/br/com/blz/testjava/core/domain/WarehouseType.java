package br.com.blz.testjava.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WarehouseType {

    ECOMMERCE,
    PHYSICAL_STORE,
    OTHER

}
