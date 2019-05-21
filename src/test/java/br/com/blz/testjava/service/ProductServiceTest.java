package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productServiceMock;

    @Mock
    private ProductRepository repositoryMock;

    @Mock
    private WarehouseService warehouseServiceMock;

    @Test
    public void createTest() throws Exception {
        when(repositoryMock.findById(any())).thenReturn(Optional.ofNullable(null));

        productServiceMock.createOrUpdate(new ProductDto());

        verify(repositoryMock, times(1)).save(any());
        verify(warehouseServiceMock, times(1)).createWarehouse(any(), any());
    }

    @Test
    public void updateTest() throws Exception {
        when(repositoryMock.findById(any())).thenReturn(Optional.ofNullable(new Product()));

        productServiceMock.createOrUpdate(new ProductDto());

        verify(warehouseServiceMock, times(1)).removeBySku(any());
        verify(repositoryMock, times(1)).delete(any());

        verify(repositoryMock, times(1)).save(any());
        verify(warehouseServiceMock, times(1)).createWarehouse(any(), any());
    }

    @Test
    public void deleteTest() throws Exception {
        when(repositoryMock.findById(any())).thenReturn(Optional.ofNullable(new Product()));

        productServiceMock.delete(123456L);

        verify(warehouseServiceMock, times(1)).removeBySku(any());
        verify(repositoryMock, times(1)).delete(any());
    }

    @Test(expected = Exception.class)
    public void deleteFalseTest() throws Exception {
        when(repositoryMock.findById(any())).thenReturn(Optional.ofNullable(null));
        productServiceMock.delete(123456L);
    }

    @Test
    public void retriverTest() throws Exception {
        Product product = new Product();
        product.setSku(123456L);
        product.setName("Sample 1");
        when(repositoryMock.findById(any())).thenReturn(Optional.ofNullable(product));

        List<Warehouse> warehouses = new ArrayList();
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("SP");
        warehouse.setQuantity(6);
        warehouse.setType("PHYSICAL_STORE");
        warehouses.add(warehouse);
        when(warehouseServiceMock.getWarehouses(any())).thenReturn(warehouses);

        ProductDto productDto = productServiceMock.retriver(123456L);

        long skuExpected = 123456L;
        String nameExpected = "Sample 1";

        assertEquals("SKU não compatível", skuExpected, productDto.getSku().longValue());
        assertTrue("Falha ao montar o dna", nameExpected.equals(productDto.getName()));
        assertTrue("isMarketable incorreto", productDto.isMarketable());

        int quantityExpected = 6;
        assertEquals("Quantidade não compatível", quantityExpected, productDto.getInventory().getQuantity());
    }
}
