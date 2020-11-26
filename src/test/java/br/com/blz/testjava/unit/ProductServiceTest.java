package br.com.blz.testjava.unit;

import br.com.blz.testjava.business.impl.ProductServiceImpl;
import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.exception.ExistingProductException;
import br.com.blz.testjava.exception.InvalidProductSKUException;
import br.com.blz.testjava.exception.NonExistingProductException;
import br.com.blz.testjava.exception.NullProductException;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.unit.compose.ProductCompose;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceTest extends BaseTest {

    private static final Product PRODUCT_DEFAULT = ProductCompose.getProductDefault();

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository repository;

    @Test
    void findProduct_whenSkuIsNull_ThenThrowException() {

        //scenario
        String sku = null;

        //action
        InvalidProductSKUException invalidProductSKUException = assertThrows(
            InvalidProductSKUException.class,
            () -> service.findProduct(sku)
        );

        //validation
        assertEquals(InvalidProductSKUException.MSG, invalidProductSKUException.getMessage());

        verify(repository, never()).findProduct(any(String.class));
        verify(repository, never()).deleteProduct(any(String.class));
        verify(repository, never()).updateProduct(any(Product.class));
        verify(repository, never()).createProduct(any(Product.class));

        verifyNoMoreInteractions(repository);
    }

    @Test
    void findProduct_whenProductNotExists_ThenReturnEmptyProduct() throws InvalidProductSKUException {

        //scenario
        String sku = "1234";
        when(repository.findProduct(any(String.class))).thenReturn(new Product());

        //action
        Product product = service.findProduct(sku);

        //validation
        assertEquals(new Product(), product);

        verify(repository).findProduct(any(String.class));
        verify(repository, never()).deleteProduct(any(String.class));
        verify(repository, never()).updateProduct(any(Product.class));
        verify(repository, never()).createProduct(any(Product.class));

        verifyNoMoreInteractions(repository);
    }

    @Test
    void findProduct_Succeeds_WhenProductExists() throws InvalidProductSKUException {

        //scenario
        String sku = PRODUCT_DEFAULT.getSku();
        when(repository.findProduct(any(String.class))).thenReturn(PRODUCT_DEFAULT);

        //action
        Product product = service.findProduct(sku);

        //validation
        assertEquals(PRODUCT_DEFAULT, product);

        verify(repository).findProduct(any(String.class));
        verify(repository, never()).deleteProduct(any(String.class));
        verify(repository, never()).updateProduct(any(Product.class));
        verify(repository, never()).createProduct(any(Product.class));

        verifyNoMoreInteractions(repository);
    }

    @Test
    void createProduct_whenProductAlreadyExists_ThenThrowException() {

        //scenario
        Product randomProduct = ProductCompose.getProductRandom();
        String sku = randomProduct.getSku();
        when(repository.findProduct(any(String.class))).thenReturn(randomProduct);

        //action
        ExistingProductException existingProductException = assertThrows(
            ExistingProductException.class,
            () -> service.createProduct(randomProduct)
        );

        //validation
        assertEquals(String.format(ExistingProductException.MSG, sku), existingProductException.getMessage());

        verify(repository).findProduct(any(String.class));
        verify(repository, never()).deleteProduct(any(String.class));
        verify(repository, never()).updateProduct(any(Product.class));
        verify(repository, never()).createProduct(any(Product.class));

        verifyNoMoreInteractions(repository);

    }

    @Test
    void createProduct_Succeeds_WhenProductDoesNotExists() throws ExistingProductException, InvalidProductSKUException, NullProductException {

        //scenario
        Product randomProduct = ProductCompose.getProductRandom();
        when(repository.findProduct(any(String.class))).thenReturn(new Product());
        when(repository.createProduct(any(Product.class))).thenReturn(randomProduct);

        //action
        Product product = service.createProduct(randomProduct);

        //validation
        assertEquals(randomProduct, product);

        verify(repository).findProduct(any(String.class));
        verify(repository, never()).deleteProduct(any(String.class));
        verify(repository, never()).updateProduct(any(Product.class));
        verify(repository).createProduct(any(Product.class));

        verifyNoMoreInteractions(repository);

    }

    @Test
    void updateProduct_whenProductDoesNotExists_ThenThrowException() {
        //scenario
        Product randomProduct = ProductCompose.getProductRandom();
        when(repository.findProduct(any(String.class))).thenReturn(null);

        //action
        NonExistingProductException nonExistingProductException = assertThrows(
            NonExistingProductException.class,
            () -> service.updateProduct(randomProduct)
        );

        //validation
        assertEquals(String.format(NonExistingProductException.MSG, randomProduct.getSku()), nonExistingProductException.getMessage());

        verify(repository, never()).deleteProduct(any(String.class));
        verify(repository, never()).createProduct(any(Product.class));
        verify(repository).findProduct(any(String.class));
        verify(repository, never()).updateProduct(any(Product.class));

        verifyNoMoreInteractions(repository);
    }

    @Test
    void updateProduct_Succeeds_WhenProductExists() throws InvalidProductSKUException, NonExistingProductException {

        //scenario
        Product randomProduct = ProductCompose.getProductRandom();
        when(repository.findProduct(any(String.class))).thenReturn(randomProduct);
        when(repository.updateProduct(any(Product.class))).thenReturn(randomProduct);

        //action
        Product product = service.updateProduct(randomProduct);

        //validation
        assertEquals(randomProduct, product);

        verify(repository).findProduct(any(String.class));
        verify(repository, never()).deleteProduct(any(String.class));
        verify(repository).updateProduct(any(Product.class));
        verify(repository, never()).createProduct(any(Product.class));

        verifyNoMoreInteractions(repository);

    }

    @Test
    void deleteProduct_whenProductDoesNotExists_ThenThrowException() {

        //scenario
        Product randomProduct = ProductCompose.getProductRandom();
        String sku = randomProduct.getSku();
        when(repository.findProduct(any(String.class))).thenReturn(null);

        //action
        NullProductException nullProductException = assertThrows(
            NullProductException.class,
            () -> service.removeProduct(sku)
        );

        //validation
        assertEquals(NullProductException.MSG, nullProductException.getMessage());

        verify(repository, never()).deleteProduct(any(String.class));
        verify(repository, never()).createProduct(any(Product.class));
        verify(repository).findProduct(any(String.class));
        verify(repository, never()).updateProduct(any(Product.class));

        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteProduct_Succeeds_WhenProductExists() throws InvalidProductSKUException, NullProductException {

        //scenario
        Product randomProduct = ProductCompose.getProductRandom();
        String sku = randomProduct.getSku();
        when(repository.findProduct(any(String.class))).thenReturn(randomProduct);
        doNothing().when(repository).deleteProduct(any(String.class));

        //action
        service.removeProduct(sku);

        //validation
        ArgumentCaptor<String> captor = forClass(String.class);
        verify(repository).deleteProduct(captor.capture());
        String skuCaptured = captor.getValue();

        assertEquals(sku, skuCaptured);

        verify(repository).findProduct(any(String.class));
        verify(repository).deleteProduct(any(String.class));
        verify(repository, never()).updateProduct(any(Product.class));
        verify(repository, never()).createProduct(any(Product.class));

        verifyNoMoreInteractions(repository);
    }

}
