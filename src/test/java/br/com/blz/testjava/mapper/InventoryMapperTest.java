package br.com.blz.testjava.mapper;

import br.com.blz.testjava.dto.InventoryDto;
import br.com.blz.testjava.dto.WarehouseDto;
import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.enums.WarehouseTypeEnum;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class InventoryMapperTest {

    @InjectMocks
    private InventoryMapper inventoryMapper;

    @Mock
    private WarehouseMapper warehouseMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ormToDto_mustConvert() {

        Warehouse warehouse = new Warehouse(1L, "SP", 10, WarehouseTypeEnum.ECOMMERCE);
        Inventory inventory = new Inventory(10L, Arrays.asList());

        Mockito.when(warehouseMapper.ormToDto(warehouse)).thenReturn(new WarehouseDto("SP", 10, WarehouseTypeEnum.PHYSICAL_STORE));

        InventoryDto inventoryDto = inventoryMapper.ormToDto(inventory);

        assertThat(inventory.getWarehouses().size(), is(inventoryDto.getWarehouses().size()));

    }

    @Test
    public void dtoToOrm_mustConvert() {

        List<WarehouseDto> warehouseDtos = Arrays.asList(new WarehouseDto("SP", 10, WarehouseTypeEnum.PHYSICAL_STORE));
        InventoryDto inventoryDto = new InventoryDto(10, warehouseDtos);

        Inventory inventory = inventoryMapper.dtoToOrm(inventoryDto);

        assertThat(inventoryDto.getWarehouses().size(), is(inventory.getWarehouses().size()));

    }
}
