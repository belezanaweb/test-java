package br.com.blz.testjava.model

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class Warehouse {

    @NotNull
    @Size(min = 1,max = 500)
    String locality;

    @NotNull
    @Min(value = 0l)
    Integer quantity;

    @NotNull
    @Size(min = 1)
    String type;
}
