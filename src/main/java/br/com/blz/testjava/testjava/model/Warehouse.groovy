package br.com.blz.testjava.testjava.model

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class Warehouse {

    @NotNull
    @Size(min = 1,max = 500)
    String locality;

    @NotNull
    @Size(min =1)
    Integer quantity;

    @NotNull
    @Size(min = 1)
    String type;
}
