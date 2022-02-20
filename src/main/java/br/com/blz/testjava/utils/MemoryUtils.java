package br.com.blz.testjava.utils;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.BoticarionBadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MemoryUtils {

    private static Map<String, Product> productMap = new HashMap<>();


    public static void persistProduct(Product product) {

        if (productMap.containsKey(product.getSku()))
            throw new BoticarionBadRequestException("Duplicated Sku: [ " + product.getSku() + " ]");
        productMap.put(product.getSku(), product);

        log.info("ProductDetails Created :" + product);
    }

    public static void updateBySku(Product product) {
        if (productMap.containsKey(product.getSku())) {
            productMap.remove(product.getSku());
            productMap.put(product.getSku(), product);
            log.info("ProductDetails Updated :" + product);
        }
    }

    public static void deleteBySku(String sku) {
        if (productMap.containsKey(sku)) {
            productMap.remove(sku);
            log.info("ProductDetails Removed :" + sku);
        }
    }

    public static Product findBySku(String sku) {
        if (MapUtils.isNotEmpty(productMap) && productMap.containsKey(sku)) {
            log.info("ProductDetails Founded :" + sku);
            return productMap.get(sku);
        }
        return null;
    }
}
