package br.com.blz.testjava.api.product;

import br.com.blz.testjava.api.mapper.MapperUtil;
import br.com.blz.testjava.api.product.controller.domain.ProductRequest;
import br.com.blz.testjava.api.product.controller.domain.ProductResponse;
import br.com.blz.testjava.api.product.entity.ProductEntity;
import br.com.blz.testjava.api.product.repository.ProductRepository;
import br.com.blz.testjava.api.product.service.ProductService;
import br.com.blz.testjava.common.exceptions.ProductAlreadyExistsException;
import br.com.blz.testjava.common.exceptions.ProductNotFoundException;
import br.com.blz.testjava.testUtils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
  @Mock private ProductRepository repository;
  @Mock private MapperUtil mapper;
  @InjectMocks private ProductService productService;

  @Test
  void create_product_should_succeed() throws ProductAlreadyExistsException {
    ProductRequest productRequest = TestUtils.getProductRequest();
    ProductEntity productEntity = new ProductEntity();
    ProductResponse productResponse = TestUtils.getProductResponse();
    when(repository.save(productEntity)).thenReturn(productEntity);
    when(mapper.productRequestToEntity(productRequest)).thenReturn(productEntity);
    when(mapper.productEntitytoResponse(productEntity)).thenReturn(productResponse);

    ProductResponse response = productService.create(productRequest);

    assertEquals(response, productResponse);
  }

  @Test
  void get_product_should_succeed() throws ProductNotFoundException {
    ProductEntity productEntity = TestUtils.getProductEntity();
    ProductResponse productResponse = TestUtils.getProductResponse();
    when(repository.findBySku(1)).thenReturn(Optional.of(productEntity));
    when(mapper.productEntitytoResponse(productEntity)).thenReturn(productResponse);

    ProductResponse response = productService.getProduct(1);

    assertEquals(response, productResponse);
  }

  @Test
  void getProduct_whenProductExists_shouldReturnProduct() throws ProductNotFoundException {
    // Arrange
    ProductEntity productEntity = new ProductEntity();
    ProductResponse expectedProductResponse = new ProductResponse();
    when(repository.findBySku(anyInt())).thenReturn(Optional.of(productEntity));
    when(mapper.productEntitytoResponse(productEntity)).thenReturn(expectedProductResponse);

    // Act
    ProductResponse actualProductResponse = productService.getProduct(123);

    // Assert
    assertEquals(expectedProductResponse, actualProductResponse);
  }

  @Test
  void getProduct_whenProductDoesNotExist_shouldThrowProductNotFoundException() {
    // Arrange
    when(repository.findBySku(anyInt())).thenReturn(Optional.empty());
    // Act & Assert
    assertThrows(ProductNotFoundException.class, () -> productService.getProduct(123));
  }
}
