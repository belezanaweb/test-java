package br.com.blz.testjava.service.impl;

import br.com.blz.testjava.domain.entity.Product;
import br.com.blz.testjava.domain.objectvalue.Inventory;
import br.com.blz.testjava.domain.objectvalue.Warehouse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Spy
    @InjectMocks
    private ProductServiceImpl productService;

    @Spy
    private Product product;

    @Spy
    private Inventory inventory;

    @Mock
    private Warehouse warehouse;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void defineMarketableTrue() {

        when(product.getInventory()).thenReturn(inventory);
        when(inventory.getQuantity()).thenReturn(1);

        productService.defineMarketable(product);

        assertTrue(product.isMarketable());
    }

    @Test
    public void defineMarketableFalse() {

        when(product.getInventory()).thenReturn(inventory);
        when(inventory.getQuantity()).thenReturn(0);

        productService.defineMarketable(product);

        assertFalse(product.isMarketable());
    }

    @Test
    public void calculateQuantity() {

        List<Warehouse> warehousesCalculate = new ArrayList<>();

        when(warehouse.getQuantity()).thenReturn(5);

        warehousesCalculate.add(warehouse);
        warehousesCalculate.add(warehouse);

        when(product.getInventory()).thenReturn(inventory);
        when(inventory.getWarehouses()).thenReturn(warehousesCalculate);

        doNothing().when(productService).defineMarketable(any(Product.class));


        productService.calculateQuantity(product);

        assertThat(inventory.getQuantity(), is(10));
    }



}
