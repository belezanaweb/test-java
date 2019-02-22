package br.com.blz.testjava;


import br.com.blz.testjava.entities.Inventory;
import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.entities.Type;
import br.com.blz.testjava.entities.Warehouse;
import br.com.blz.testjava.services.ProductService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductService populationService;

    @Test
    public void findProductBySkuTest() {
        Product productReturn = new Product();
        productReturn.setId(1l);
        productReturn.setSku(1234L);
        productReturn.setName("LOreal Professionnel Expert Absolut Repair Cortex Lipidium");
        productReturn.setInventory(new Inventory());
        List<Warehouse> warehouseList = new ArrayList<>();
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("SP");
        warehouse.setQuantity(14);
        warehouse.setType(Type.ECOMMERCE);
        warehouseList.add(warehouse);
        productReturn.getInventory().setWarehouses(warehouseList);
        productReturn.getInventory().setQuantity(14);
        productReturn.setIsMarketable(Boolean.TRUE);

        given(this.populationService.findBySku(1234L)).willReturn(Optional.of(productReturn));


        Product product = this.populationService.findBySku(1234L).get();
        assertNotNull(product.getId());
        assertEquals(1234L, product.getSku());
        assertEquals("LOreal Professionnel Expert Absolut Repair Cortex Lipidium", product.getName());
        assertThat(product.getInventory().getWarehouses(), not(empty()));
        assertEquals(Boolean.TRUE, product.getIsMarketable());
        assertThat(product.getInventory().getQuantity().intValue(), greaterThan(0));
    }

}
