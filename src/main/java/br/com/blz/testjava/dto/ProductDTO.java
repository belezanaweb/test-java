package br.com.blz.testjava.dto;

import br.com.blz.testjava.model.Inventory;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductDTO {
    private Integer sku;
    private String name;
    private Inventory inventory;
}
