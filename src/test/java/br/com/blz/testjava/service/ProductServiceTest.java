package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.api.request.CreateProductRequest;
import br.com.blz.testjava.domain.api.request.ReplaceProductRequest;
import br.com.blz.testjava.domain.api.response.CreateProductResponse;
import br.com.blz.testjava.domain.api.response.FindProductResponse;
import br.com.blz.testjava.domain.exception.ConflictException;
import br.com.blz.testjava.domain.exception.NotFoundException;
import br.com.blz.testjava.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

    private String sku = "123";
    private String nonNumericSku = "abc";

    @Test
    public void createProduct_success() {
        CreateProductRequest mockRequest = mock(CreateProductRequest.class);
        CreateProductResponse expectedResponse = mock(CreateProductResponse.class);

        when(repository.findBySku(anyLong())).thenReturn(Optional.empty());
        when(repository.insert(mockRequest)).thenReturn(mockRequest);
        when(mockRequest.toCreateResponse()).thenReturn(expectedResponse);

        CreateProductResponse response = service.createProduct(mockRequest);

        assertEquals(expectedResponse, response);
    }

    @Test(expected = ConflictException.class)
    public void createProduct_failed_whenProductAlreadyExists() {
        CreateProductRequest mockRequest = mock(CreateProductRequest.class);

        when(repository.findBySku(anyLong())).thenReturn(Optional.of(mockRequest));

        service.createProduct(mockRequest);
    }

    @Test
    public void findProduct_success() {
        CreateProductRequest mockProduct = mock(CreateProductRequest.class);
        FindProductResponse expectedResponse = mock(FindProductResponse.class);

        when(repository.findBySku(anyLong())).thenReturn(Optional.of(mockProduct));
        when(mockProduct.toFindResponse()).thenReturn(expectedResponse);

        FindProductResponse response = service.findProduct(sku);

        assertEquals(expectedResponse, response);
    }

    @Test(expected = NotFoundException.class)
    public void findProduct_failed_whenProductNotFound() {
        when(repository.findBySku(anyLong())).thenReturn(Optional.empty());

        service.findProduct(sku);
    }

    @Test(expected = NotFoundException.class)
    public void findProduct_failed_whenSkuNotANumber() {
        service.findProduct(nonNumericSku);
    }

    @Test
    public void replaceProduct_success_whenProductDoesNotExists() {
        ReplaceProductRequest mockRequest = mock(ReplaceProductRequest.class);
        CreateProductRequest mockProduct = mock(CreateProductRequest.class);
        CreateProductResponse expectedResponse = mock(CreateProductResponse.class);

        when(repository.findBySku(anyLong())).thenReturn(Optional.empty());
        when(repository.insert(any(CreateProductRequest.class))).thenReturn(mockProduct);
        when(mockProduct.toCreateResponse(anyBoolean())).thenReturn(expectedResponse);

        CreateProductResponse response = service.replaceProduct(sku, mockRequest);

        assertEquals(expectedResponse, response);
        verify(mockProduct).toCreateResponse(false);
    }

    @Test
    public void replaceProduct_success_whenProductAlreadyExists() {
        ReplaceProductRequest mockRequest = mock(ReplaceProductRequest.class);
        CreateProductRequest mockProduct = mock(CreateProductRequest.class);
        CreateProductResponse expectedResponse = mock(CreateProductResponse.class);

        when(repository.findBySku(anyLong())).thenReturn(Optional.of(mockProduct));
        when(repository.insert(any(CreateProductRequest.class))).thenReturn(mockProduct);
        when(mockProduct.toCreateResponse(anyBoolean())).thenReturn(expectedResponse);

        CreateProductResponse response = service.replaceProduct(sku, mockRequest);

        assertEquals(expectedResponse, response);
        verify(mockProduct).toCreateResponse(true);
    }

    @Test(expected = NotFoundException.class)
    public void replaceProduct_failed_whenSkuNotANumber() {
        ReplaceProductRequest mockRequest = mock(ReplaceProductRequest.class);

        service.replaceProduct(nonNumericSku, mockRequest);
    }

    @Test
    public void deleteProduct_success() {
        when(repository.delete(anyLong())).thenReturn(Optional.of(mock(CreateProductRequest.class)));

        service.deleteProduct(sku);
    }

    @Test(expected = NotFoundException.class)
    public void deleteProduct_failed_whenProductNotFound() {
        when(repository.delete(anyLong())).thenReturn(Optional.empty());

        service.deleteProduct(sku);
    }

    @Test(expected = NotFoundException.class)
    public void deleteProduct_failed_whenSkuNotANumber() {
        service.deleteProduct(nonNumericSku);
    }
}
