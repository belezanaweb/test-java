package br.com.blz.testjava.inventory;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class InventoryServiceTest {

    @Mock
    private InventoryRepository repository;
    @InjectMocks
    private InventoryService service;

    @Test
    public void findProductBySku_shouldReturnSumOfQuantityInventory() {
        Warehouse warehouse1 = Warehouse.builder()
                .locality("SP")
                .quantity(12)
                .types(Type.ECOMMERCE)
                .build();

        Warehouse warehouse2 = Warehouse.builder()
                .locality("SP")
                .quantity(3)
                .types(Type.PHYSICAL_STORE)
                .build();

        Inventory inventory1 = Inventory.builder()
                .name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")
                .sku(1L)
                .warehouses(Arrays.asList(warehouse1, warehouse2))
                .build();

        Mockito.when(service.findByProduct(Mockito.anyLong())).thenReturn(inventory1);
        Inventory inventory = service.findByProduct(2L);
        assertThat(inventory.getQuantity(), Matchers.equalTo(15));
    }

    @Test
    public void findProductBySku_shouldReturnIfIsMarktableTrue() {
        Warehouse warehouse1 = Warehouse.builder()
                .locality("SP")
                .quantity(12)
                .types(Type.ECOMMERCE)
                .build();

        Warehouse warehouse2 = Warehouse.builder()
                .locality("SP")
                .quantity(3)
                .types(Type.PHYSICAL_STORE)
                .build();

        Inventory inventory1 = Inventory.builder()
                .name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")
                .sku(1L)
                .warehouses(Arrays.asList(warehouse1, warehouse2))
                .build();
        Mockito.when(service.findByProduct(Mockito.anyLong())).thenReturn(inventory1);
        Inventory inventory = service.findByProduct(2L);
        assertThat(inventory.isMarktable(), Matchers.equalTo(true));
    }
}