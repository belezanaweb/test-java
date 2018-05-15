package br.com.blz.testjava.inventory;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceTest {

    @Autowired
    private InventoryService service;

    @Before
    public void setUp() throws Exception {
        Inventory inventory = new Inventory();
        inventory.setName("jajaj");
        inventory.setSku(1L);
        service.save(inventory);
    }

    @Test
    public void findProduct_xxx_xxx() {
        Inventory inventory = service.findByProduct(1L);
        Assert.assertThat(inventory, Matchers.notNullValue());
        Assert.assertThat(inventory.getName(), Matchers.equalTo("jajaj"));
    }
}