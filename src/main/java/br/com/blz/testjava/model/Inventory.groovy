package br.com.blz.testjava.model

import javax.validation.Valid

class Inventory {

    Long quantity;

    @Valid
    List<Warehouse> warehouses;
}
