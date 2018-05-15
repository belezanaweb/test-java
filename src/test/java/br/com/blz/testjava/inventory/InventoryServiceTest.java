package br.com.blz.testjava.inventory;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceTest {

    @Autowired
    private InventoryService service;

    @Before
    public void setUp() throws Exception {
        Inventory inventory1 = new Inventory();
        inventory1.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
        inventory1.setSku(1L);

        Inventory inventory2 = new Inventory();
        inventory2.setName("Kérastase Spécifique Bain Vital Dermo-Calm Edição Limitada - Shampoo 500ml");
        inventory2.setSku(2L);

        service.save(inventory1);
        service.save(inventory2);
    }

    @Test
    public void findProductAll_shouldReturnAllInventories() {
        List<Inventory> inventory = service.findAll();
        assertThat(inventory, Matchers.hasSize(2));
    }

    @Test
    public void findProductBySku_shouldReturnInventory() {
        Inventory inventory = service.findByProduct(1L);
        assertThat(inventory, Matchers.notNullValue());
        assertThat(inventory.getName(),
                Matchers.equalTo("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g"));
    }

    @Test
    public void findProductBySku_shouldReturnSumOfQuantityInventory() {
        Inventory inventory = service.findByProduct(2L);
        Assert.assertThat(inventory.getQuantity(), Matchers.equalTo(15));
    }
}