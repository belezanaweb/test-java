package br.com.blz.testjava.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Warehouse {

    private Long id;
    private String locality;
    private Integer quantity;
    private String type;
}
