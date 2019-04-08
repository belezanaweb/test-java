package br.com.blz.testjava.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "product")
public class Product {

    @Id
    private Integer sku;
    private String name;
    private Inventory inventory;
    private Boolean marketable;

}
