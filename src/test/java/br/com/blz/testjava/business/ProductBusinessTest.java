package br.com.blz.testjava.business;

import br.com.blz.testjava.TestJavaApplication;
import br.com.blz.testjava.enums.WarehouseType;
import br.com.blz.testjava.exception.SkuExistsException;
import br.com.blz.testjava.exception.SkuNotExistsException;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static br.com.blz.testjava.business.util.MockObjects.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ProductBusiness.class, ProductService.class, ProductRepository.class, TestJavaApplication.class})
class ProductBusinessTest {

    @Autowired
    private ProductBusiness productBusiness;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void before() {
        this.productRepository.deleteAll();
    }

    @Test
    void mustSavedProductSuccessfullyAndReturnsMarketableTrueAndInvetoryQuantity() {
        var warehouses = List.of(createWarehouse("SP", WarehouseType.PHYSICAL_STORE,
            250l));
        var inventory = createInventory(warehouses);
        var product = createProduct("ps5 digital edition", 12234l, inventory);

        var productReturn = this.productBusiness.save(product);

        assertAll(
            () -> assertTrue(productReturn.isMarketable()),
            () -> assertEquals(250l, productReturn.getInventory().getQuantity()));
    }

    @Test
    void mustSavedProductSuccessfullyAndReturnisMarketableFalseAndInvetoryQuantity() {
        var inventory = createInventory(null);
        var product = createProduct("ps5 digital edition", 12234l, inventory);

        var productReturn = this.productBusiness.save(product);

        assertAll(
            () -> assertFalse(productReturn.isMarketable()),
            () -> assertEquals(0, productReturn.getInventory().getQuantity()));
    }


    @Test
    void mustThrowExceptionBecauseProductExistsInBase() {
        var warehouses = List.of(createWarehouse("SP", WarehouseType.PHYSICAL_STORE,
            250l));
        var inventory = createInventory(warehouses);
        var product = createProduct("ps5 digital edition", 12234l, inventory);

        this.productBusiness.save(product);

        assertThrows(SkuExistsException.class, () -> this.productBusiness.save(product));

    }

    @Test
    void mustReturnsProductBySku() {
        var warehouses = List.of(createWarehouse("SP", WarehouseType.PHYSICAL_STORE,
            250l));
        var inventory = createInventory(warehouses);
        var product = createProduct("ps5 digital edition", 12234l, inventory);

        this.productBusiness.save(product);
        var productReturn = this.productBusiness.findBySku(12234l);

        Assertions.assertAll(
            () -> Assertions.assertTrue(productReturn.isMarketable()),
            () -> Assertions.assertEquals(250l, productReturn.getInventory().getQuantity()),
            () -> Assertions.assertEquals(product.getName(), productReturn.getName()));
    }

    @Test
    void mustThrowExceptionBecauseProductBySkuNotExistsInBase() {
        Assertions.assertThrows(SkuNotExistsException.class, () -> this.productBusiness.findBySku(12234l));
    }

}
