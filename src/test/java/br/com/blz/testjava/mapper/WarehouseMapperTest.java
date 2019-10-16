package br.com.blz.testjava.mapper;

import br.com.blz.testjava.dto.WarehouseDto;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.enums.WarehouseTypeEnum;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class WarehouseMapperTest {

    @InjectMocks
    private WarehouseMapper warehouseMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void dtoToOrm_mustConvert() {

        Warehouse warehouse = new Warehouse(12L, "SP", 10, WarehouseTypeEnum.ECOMMERCE);
        WarehouseDto warehouseDto = warehouseMapper.ormToDto(warehouse);

        assertThat(warehouse.getLocality(), is(warehouseDto.getLocality()));
        assertThat(warehouse.getQuantity(), is(warehouseDto.getQuantity()));
        assertThat(warehouse.getType(), is(warehouseDto.getType()));

    }

    @Test
    public void ormToDto_mustConvert() {

        WarehouseDto warehouseDto = new WarehouseDto("SP", 10, WarehouseTypeEnum.PHYSICAL_STORE);
        Warehouse warehouse = warehouseMapper.dtoToOrm(warehouseDto);

        assertThat(warehouseDto.getQuantity(), is(warehouse.getQuantity()));
        assertThat(warehouseDto.getLocality(), is(warehouse.getLocality()));
        assertThat(warehouseDto.getType(), is(warehouse.getType()));

    }
}
