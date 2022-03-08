package br.com.blz.testjava.service;

import br.com.blz.testjava.dto.in.InventoryDTOIn;
import br.com.blz.testjava.dto.in.ProductDTOIn;
import br.com.blz.testjava.dto.in.WarehouseDTOIn;
import br.com.blz.testjava.dto.out.ProductDTOOut;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exceptions.ProductFoundException;
import br.com.blz.testjava.exceptions.ProductNotFoundException;
import br.com.blz.testjava.repository.IProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ProductService.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceTest {

    ProductDTOIn dummyProductDTOIn;

    InventoryDTOIn dummyInventoryDTOIn;

    WarehouseDTOIn firstDummyWarehouseDTOIn;

    WarehouseDTOIn secondDummyWarehouseDTOIn;

    Product dummyProduct;

    @MockBean
    private IProductRepository productRepository;

    @SpyBean
    private ProductService productService;

    @BeforeAll
    public void initializeMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void resetDummies() {
        this.dummyInventoryDTOIn = new InventoryDTOIn();

        this.dummyProductDTOIn = new ProductDTOIn();
        this.dummyProductDTOIn.setSku(123);
        this.dummyProductDTOIn.setName("dummy_name");

        this.firstDummyWarehouseDTOIn = new WarehouseDTOIn("SP", 10, "ECOMMERCE");
        this.secondDummyWarehouseDTOIn = new WarehouseDTOIn("RS", 5, "PHYSICAL_STORE");

        this.dummyInventoryDTOIn.getWarehouses().add(this.firstDummyWarehouseDTOIn);
        this.dummyInventoryDTOIn.getWarehouses().add(this.secondDummyWarehouseDTOIn);

        this.dummyProductDTOIn.setInventory(this.dummyInventoryDTOIn);

        this.dummyProduct = new Product(this.dummyProductDTOIn);

    }

    @Test
    void should_throw_product_found_exception_when_creating() {

        when(this.productRepository.get(anyInt())).thenReturn(new Product());
        assertThrows(ProductFoundException.class, () -> this.productService.create(dummyProductDTOIn));
    }

    @Test
    void should_save_successfully_when_creating() throws ProductFoundException {
        this.productService.create(dummyProductDTOIn);
    }

    @Test
    void should_throw_product_not_found_exception_when_getting() {
        when(this.productRepository.get(anyInt())).thenReturn(null);
        assertThrows(ProductNotFoundException.class, () -> this.productService.get(1));
    }

    @Test
    void should_return_dto_out_succesfully_when_getting() throws ProductNotFoundException {
        when(this.productRepository.get(anyInt())).thenReturn(this.dummyProduct);
        ProductDTOOut productDTOOut = this.productService.get(1);
        assertNotNull(productDTOOut);
    }

    @Test
    void should_throw_product_not_found_exception_when_updating() {
        when(this.productRepository.get(anyInt())).thenReturn(null);
        assertThrows(ProductNotFoundException.class, () -> this.productService.update(this.dummyProductDTOIn.getSku(),
            this.dummyProductDTOIn));
    }
}
