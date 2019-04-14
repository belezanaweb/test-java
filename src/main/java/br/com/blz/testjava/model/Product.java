package br.com.blz.testjava.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
@Builder
@Data
public class Product {

    @Id
    private Integer sku;
    private String name;
    private Inventory inventory;
    private Boolean marketable;
}
