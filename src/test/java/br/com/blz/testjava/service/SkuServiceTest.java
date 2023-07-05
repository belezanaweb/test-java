package br.com.blz.testjava.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyCollection;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.com.blz.testjava.entities.Inventory;
import br.com.blz.testjava.entities.Sku;
import br.com.blz.testjava.entities.Warehouses;

@TestInstance(Lifecycle.PER_CLASS)
public class SkuServiceTest {

    private SkuService skuService = new SkuService();
    private Inventory inventory = new Inventory();
    private Sku sku = new Sku();
    private Long idSku = 43264L;
    private Integer quantities = 0;
    private List<Warehouses> list = new ArrayList<>();

    @BeforeAll
    public void init() {
        Warehouses warehouses = new Warehouses("SP", 12, "ECOMMERCE");
        Warehouses warehouses1 = new Warehouses("MOEMA", 3, "PHYSICAL_STORE");
        list.add(warehouses);
        list.add(warehouses1);
        sku = new Sku(43264L,
                "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", inventory,
                list,
                true);
    }

    @Test
    void testInsert() {
        Sku sku = skuService.insert(this.sku);
        assertEquals(sku, sku);
    }

    @Test
    void testUpdate() {
        Sku sku = skuService.update(this.idSku, this.sku);
        assertEquals(sku, sku);
    }

    @Test
    void testFindIdSku() {
        Sku sku = skuService.findIdSku(this.idSku);
        assertEquals(sku.getSku(), sku.getSku());
    }

    @Test
    void testFindAllSku() {
        List<Sku> sku = skuService.findAllSku();
        assertEquals(anyCollection(), sku);
        assertEquals(2, list.size());
    }

    @Test
    void testSumQuantity() {
        skuService.sumQuantity(this.quantities);
        assertEquals(15, 15);
    }

    @Test
    void testDelete() {
        skuService.delete(this.idSku);
        assertEquals(sku, sku);
    }

}
