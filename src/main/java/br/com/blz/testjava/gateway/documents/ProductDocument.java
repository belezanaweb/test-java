package br.com.blz.testjava.gateway.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDocument {
    private Long sku;
    private String name;
    private InventoryDocument inventory;
}
