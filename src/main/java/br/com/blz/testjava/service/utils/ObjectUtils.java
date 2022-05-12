package br.com.blz.testjava.service.utils;

import br.com.blz.testjava.model.InventoryResponse;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.ProductResponse;
import java.util.List;

public class ObjectUtils {

    public static ProductResponse buildProduct(Product product) {
        InventoryResponse invetoryReponse = InventoryResponse.builder()
            .warehouses(product.getInventory().getWarehouses())
            .quantity(product.getInventory().getWarehouses().size()).build();
        ProductResponse response = ProductResponse.builder()
            .sku(product.getSku())
            .name(product.getName())
            .inventory(invetoryReponse)
            .isMarketable(product.getInventory().getWarehouses().size() > 0).build();
        return response;
    }

    public static boolean isContains(Product product, List<ProductResponse> listResponse) {
        return listResponse.stream()
            .filter(itens -> itens.getSku().equals(product.getSku()))
            .findAny().isPresent();
    }
}
