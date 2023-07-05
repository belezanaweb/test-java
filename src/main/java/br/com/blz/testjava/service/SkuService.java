package br.com.blz.testjava.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.entities.Inventory;
import br.com.blz.testjava.entities.Sku;
import br.com.blz.testjava.exception.SkuException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SkuService {

    private Integer quantities = 0;
    private List<Sku> products = new ArrayList<>();

    public Sku insert(Sku sku) {
        try {
            if (products.isEmpty()) {
                products.add(sku);
            } else {
                for (Sku list : products) {
                    if (list.getSku().equals(sku.getSku())) {
                        throw new SkuException("This SKU already was registered!!");
                    }
                }
                products.add(sku);
            }
            log.info("SKU created!");
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return sku;
    }

    public Sku update(Long idSku, Sku sku) {
        try {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getSku().equals(idSku)) {
                    products.remove(i);
                    products.add(i, sku);
                    log.info("SKU updated!");
                    i++;
                }
            }
        } catch (Exception e) {
            log.warn("SKU not found! Not possible update this SKU {}", e.getMessage());
        }
        return sku;
    }

    public Sku findIdSku(Long idSku) {
        Sku sku = new Sku();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getSku().equals(idSku)) {
                sumQuantity(this.quantities);
                sku = products.get(i);
            }
        }
        return sku;
    }

    public List<Sku> findAllSku() {
        sumQuantity(this.quantities);
        return products;
    }

    public Integer sumQuantity(Integer quantities) {
        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < products.get(i).getWarehouses().size(); j++) {
                quantities += products.get(i).getWarehouses().get(j).getQuantity();
            }
            if (quantities > 0) {
                Inventory inventory = new Inventory(quantities);
                products.get(i).setInventory(inventory);
                products.get(i).setIsMarketable(true);
            }
            quantities = 0;
        }
        return quantities;
    }

    public void delete(Long skuId) {
        try {
            for (int i = 0; i <= products.size(); i++) {
                if (products.get(i).getSku().equals(skuId)) {
                    products.remove(i);
                    log.info("SKU deleted!");
                    i++;
                }
            }
        } catch (Exception e) {
            log.warn("SKU not found: {}", e.getMessage());
        }
    }

}
