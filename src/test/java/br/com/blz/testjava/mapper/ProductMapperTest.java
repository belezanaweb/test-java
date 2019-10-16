package br.com.blz.testjava.mapper;

import br.com.blz.testjava.dto.InventoryDto;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.dto.WarehouseDto;
import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.enums.WarehouseTypeEnum;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class ProductMapperTest {

    @InjectMocks
    private ProductMapper productMapper;

    @Mock
    private InventoryMapper inventoryMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ormToDto_mustConvert() {

        Warehouse warehouse = new Warehouse(1L, "SP", 10, WarehouseTypeEnum.ECOMMERCE);
        Inventory inventory = new Inventory(10L, Arrays.asList());
        Product product = new Product(123L, "Test", inventory);

        ProductDto productDto = productMapper.ormToDto(product);

        Assert.assertThat(product.getSku(), CoreMatchers.is(productDto.getSku()));
        Assert.assertThat(product.getName(), CoreMatchers.is(productDto.getName()));

    }

    @Test
    public void dtoToOrm_mustConvert() {

        List<WarehouseDto> warehouseDtos = Arrays.asList(new WarehouseDto("SP", 10, WarehouseTypeEnum.PHYSICAL_STORE));
        InventoryDto inventoryDto = new InventoryDto(10, warehouseDtos);
        ProductDto productDto = new ProductDto(123L, "Test", inventoryDto, Boolean.TRUE);

        Product product = productMapper.dtoToOrm(productDto);

        Assert.assertThat(productDto.getSku(), CoreMatchers.is(product.getSku()));
        Assert.assertThat(productDto.getName(), CoreMatchers.is(product.getName()));

    }
}
