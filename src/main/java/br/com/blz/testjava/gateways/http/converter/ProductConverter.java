package br.com.blz.testjava.gateways.http.converter;

import br.com.blz.testjava.domains.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product convertRequestToDomain(ProductRequestJSON requestJSON){
        return Product
            .builder()
            .sku(requestJSON.getSku())
            .name(requestJSON.getName())
            .inventory(requestJSON.getInventory())
            .build();
    }

    public ProductResponseJSON convertDomainToResponse(Product domain){
        return ProductResponseJSON
            .builder()
            .sku(domain.getSku())
            .name(domain.getName())
            .marketable(domain.getMarketable())
            .inventory(domain.getInventory())
            .build();
    }

}
