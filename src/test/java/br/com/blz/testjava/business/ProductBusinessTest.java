package br.com.blz.testjava.business;

import br.com.blz.testjava.dto.InventoryDto;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.dto.WarehouseDto;
import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.enums.WarehouseTypeEnum;
import br.com.blz.testjava.exception.ResourceNotFoundException;
import br.com.blz.testjava.exception.SKUExistsException;
import br.com.blz.testjava.mapper.ProductMapper;
import br.com.blz.testjava.service.ProductService;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ProductBusinessTest {

    @InjectMocks
    private ProductBusiness productBusiness;

    @Mock
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save_withDateValid_mustSave() throws SKUExistsException {

        ProductDto productDto = this.getProductDto();
        Product product = this.getProduct();

        when(productService.findBySku(213L)).thenReturn(Optional.empty());
        when(productMapper.ormToDto(any(Product.class))).thenReturn(productDto);
        when(productMapper.dtoToOrm(any(ProductDto.class))).thenReturn(product);
        when(productService.save(any(Product.class))).thenReturn(product);

        ProductDto expected = productBusiness.save(productDto);

        assertThat(productDto.getSku(), CoreMatchers.is(expected.getSku()));

    }

    @Test(expected = SKUExistsException.class)
    public void save_whenSKUExists_mustInvokeSKUExistsException() throws SKUExistsException {

        ProductDto productDto = this.getProductDto();
        Product product = this.getProduct();

        when(productService.findBySku(productDto.getSku())).thenReturn(Optional.of(product));

        ProductDto expected = productBusiness.save(productDto);

    }

    @Test
    public void findBySku_withSku_mustReturnProductDto() throws ResourceNotFoundException {

        ProductDto productDto = this.getProductDto();
        Product product = this.getProduct();

        when(productMapper.ormToDto(any(Product.class))).thenReturn(productDto);
        when(productMapper.dtoToOrm(any(ProductDto.class))).thenReturn(product);
        when(productService.findBySku(any(Long.class))).thenReturn(Optional.of(product));

        ProductDto expected = productBusiness.findBySku(3434L);

        assertThat(product.getSku(), CoreMatchers.is(expected.getSku()));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void findBySku_whenNotFound_mustInvokeResourceNotFoundException() throws ResourceNotFoundException {

        ProductDto productDto = this.getProductDto();
        Product product = this.getProduct();

        when(productMapper.ormToDto(any(Product.class))).thenReturn(productDto);
        when(productMapper.dtoToOrm(any(ProductDto.class))).thenReturn(product);
        when(productService.findBySku(any(Long.class))).thenReturn(Optional.empty());

        ProductDto expected = productBusiness.findBySku(3434L);

    }

    @Test
    public void deleteBySku_whenFound_mustDelete() throws ResourceNotFoundException {

        Product product = this.getProduct();

        when(productService.findBySku(3434L)).thenReturn(Optional.of(product));
        productBusiness.deleteBySku(3434L);

        verify(productService, times(1)).delete(any(Product.class));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteBySku_whenNotFound_mustInvokeResourceNotFoundException() throws ResourceNotFoundException {

        when(productService.findBySku(3434L)).thenReturn(Optional.empty());
        productBusiness.deleteBySku(3434L);

    }

    @Test
    public void update_whenFound_mustUpdate() throws ResourceNotFoundException {

        ProductDto productDto = this.getProductDto();
        Product product = this.getProduct();

        when(productMapper.ormToDto(any(Product.class))).thenReturn(productDto);
        when(productMapper.dtoToOrm(any(ProductDto.class))).thenReturn(product);
        when(productService.findBySku(any(Long.class))).thenReturn(Optional.of(product));
        when(productService.save(any(Product.class))).thenReturn(product);

        ProductDto expected = productBusiness.update(productDto);

        verify(productService, times(1)).save(any(Product.class));
        assertThat(product.getSku(), CoreMatchers.is(expected.getSku()));

    }

    @Test
    public void list_whenFound_mustReturnList() {

        List<Product> products = Arrays.asList(this.getProduct());

        when(productService.list()).thenReturn(products);

        List<ProductDto> expected = productBusiness.list();

        assertThat(products.size(), CoreMatchers.is(expected.size()));

    }

    private ProductDto getProductDto() {
        WarehouseDto warehouseDto = new WarehouseDto("SP", 5, WarehouseTypeEnum.ECOMMERCE);
        InventoryDto inventoryDto = new InventoryDto(10, Arrays.asList(warehouseDto));
        ProductDto productDto = new ProductDto(3434l, "Product test", inventoryDto, Boolean.TRUE);
        return productDto;
    }

    private Product getProduct() {
        Warehouse warehouse = new Warehouse(1L, "SP", 5, WarehouseTypeEnum.ECOMMERCE);
        Inventory inventory = new Inventory(10L, Arrays.asList(warehouse));
        Product product = new Product(3434L, "Product", inventory);
        return product;
    }

}
