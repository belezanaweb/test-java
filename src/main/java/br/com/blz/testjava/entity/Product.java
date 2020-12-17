package br.com.blz.testjava.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String name;
    private boolean isMarketable;
    private Long sku;
    private Inventory inventory;
    
}
