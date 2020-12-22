package br.com.blz.testjava.service;

import br.com.blz.testjava.controller.resources.ProductRequest;
import br.com.blz.testjava.controller.resources.ProductResponse;
import br.com.blz.testjava.exception.NotFoundException;
import br.com.blz.testjava.exception.SkuAlreadyRegisteredException;
import br.com.blz.testjava.mother.ProductMother;
import br.com.blz.testjava.persistence.entity.Product;
import br.com.blz.testjava.persistence.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static br.com.blz.testjava.mother.ProductMother.createProductEntity;
import static br.com.blz.testjava.mother.ProductMother.createSimpleProductEntity;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ProductService.class)
public class ProductServiceTest {

    private static final Long DEFAULT_SKU = 123L;

    @Autowired
    private ProductService fixture;

    @MockBean
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProductBySku() {

        given(productRepository.findBySku(eq(DEFAULT_SKU))).willReturn(of(createProductEntity()));
        ProductResponse result = fixture.getProductBySku(DEFAULT_SKU);
        then(productRepository).should().findBySku(eq(DEFAULT_SKU));

        assertThat(result).isNotNull();
        assertThat(result.getSku()).isEqualTo(DEFAULT_SKU);
        assertThat(result.getMarketable()).isTrue();
        assertThat(result.getInventoryResponse().getQuantity()).isEqualTo(20);
    }

    @Test
    public void createProduct() {
        ProductRequest productRequest = ProductMother.createProductRequest();
        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);

        given(productRepository.findBySku(any())).willReturn(Optional.empty());

        fixture.createProduct(productRequest);

        then(productRepository).should().save(captor.capture());

        assertThat(captor.getValue()).isNotNull();
        assertThat(captor.getValue().getId()).isNotNull();
        assertThat(captor.getValue().getSku()).isEqualTo(productRequest.getSku());
        assertThat(captor.getValue().getName()).isEqualTo(productRequest.getName());
        assertThat(captor.getValue().getWarehouses().size()).isEqualTo(productRequest.getInventory().getWarehouse().size());
    }

    @Test
    public void createProductSkuAlreadyExists() {
        ProductRequest productRequest = ProductMother.createProductRequest();

        given(productRepository.findBySku(eq(productRequest.getSku()))).willReturn(of(createProductEntity()));

        try {
            fixture.createProduct(productRequest);
            fail("Should throw not found exception!");
        } catch (SkuAlreadyRegisteredException e) {
            assertThat(e.getMessage()).isEqualTo("Product with sku 123 already exists!");
        }

        then(productRepository).should().findBySku(eq(productRequest.getSku()));
        then(productRepository).shouldHaveNoMoreInteractions();

    }

    @Test
    public void updateProduct() {
        ProductRequest productRequest = ProductMother.createProductRequest();
        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);

        given(productRepository.findBySku(eq(productRequest.getSku()))).willReturn(of(createProductEntity()));
        given(productRepository.save(any(Product.class))).willReturn(createSimpleProductEntity());

        ProductResponse productResponse = fixture.updateProduct(productRequest);

        then(productRepository).should().findBySku(eq(productRequest.getSku()));
        then(productRepository).should().save(captor.capture());

        assertThat(captor.getValue()).isNotNull();
        assertThat(captor.getValue().getId()).isNotNull();
        assertThat(captor.getValue().getSku()).isEqualTo(productRequest.getSku());
        assertThat(captor.getValue().getName()).isEqualTo(productRequest.getName());
        assertThat(captor.getValue().getName()).isEqualTo(productRequest.getName());
        assertThat(captor.getValue().getWarehouses().size()).isEqualTo(productRequest.getInventory().getWarehouse().size());

        assertThat(productResponse.getMarketable()).isTrue();
        assertThat(productResponse.getInventoryResponse().getQuantity()).isEqualTo(10);
    }

    @Test
    public void updateProductNotFound() {
        ProductRequest productRequest = ProductMother.createProductRequest();

        given(productRepository.findBySku(eq(productRequest.getSku()))).willReturn(empty());

        try {
            fixture.updateProduct(productRequest);
            fail("Sku not exists.");
        } catch (NotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Product not found!");
        }

        then(productRepository).should().findBySku(eq(productRequest.getSku()));
        then(productRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    public void deleteProduct() {
        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);

        given(productRepository.findBySku(eq(123L))).willReturn(of(createProductEntity()));
        fixture.deleteProduct(123L);
        then(productRepository).should().findBySku(123L);
        then(productRepository).should().delete(captor.capture());

        assertThat(captor.getValue()).isNotNull();
    }
}
