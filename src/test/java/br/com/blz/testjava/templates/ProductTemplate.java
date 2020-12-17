package br.com.blz.testjava.templates;

import br.com.blz.testjava.dto.ProductDTO;
import lombok.Getter;

public class ProductTemplate extends BaseTemplate {

    @Getter private static final ProductTemplate instance = new ProductTemplate();
    
    protected final InventoryTemplate inventoryTemplate = InventoryTemplate.getInstance();
    
    public ProductDTO getValid() {
      return ProductDTO.builder()
              .isMarketable(false)
              .name(faker.options().option("Expert Absolut Repair", "Shampoo"))
              .sku(faker.options().option(12345l, 54321l))
              .inventory(inventoryTemplate.getValid())
              .build();
    }
    
}
