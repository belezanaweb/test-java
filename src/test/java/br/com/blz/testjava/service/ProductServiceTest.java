package br.com.blz.testjava.service;

import br.com.blz.testjava.database.ProductDatabase;
import br.com.blz.testjava.domain.request.ProductCreateRequest;
import br.com.blz.testjava.domain.request.ProductUpdateRequest;
import br.com.blz.testjava.domain.response.ProductResponse;
import br.com.blz.testjava.domain.response.WarehouseResponse;
import br.com.blz.testjava.exception.NotFoundException;
import br.com.blz.testjava.exception.UnprocessableEntityException;
import br.com.blz.testjava.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static br.com.blz.testjava.mother.InventoryMother.getInventory;
import static br.com.blz.testjava.mother.InventoryMother.getInventoryRequest;
import static br.com.blz.testjava.mother.ProductMother.getProduct;
import static br.com.blz.testjava.mother.ProductMother.getProductCreateRequest;
import static br.com.blz.testjava.mother.ProductMother.getProductUpdateRequest;
import static br.com.blz.testjava.mother.WarehouseMother.getWarehouse;
import static br.com.blz.testjava.mother.WarehouseMother.getWarehouseRequest;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService fixture;

    @Mock
    private ProductDatabase productDatabase;

    @Test
    public void testCreateProductSuccess() {
        ProductCreateRequest productCreateRequest = getProductCreateRequest();

        when(productDatabase.exists(productCreateRequest.getSku())).thenReturn(false);
        when(productDatabase.createProduct(any(Product.class)))
            .thenReturn(new Product(productCreateRequest));

        ProductResponse productResponse = fixture.createProduct(productCreateRequest);

        assertThat(productResponse).isNotNull();
        assertThat(productResponse.getSku()).isEqualTo(productCreateRequest.getSku());
        assertThat(productResponse.getName()).isEqualTo(productCreateRequest.getName());
        assertThat(productResponse.getInventory().getQuantity()).isEqualTo(20);
        assertThat(productResponse.getInventory().isMarketable()).isTrue();

        verify(productDatabase).exists(productCreateRequest.getSku());
        verify(productDatabase).createProduct(any(Product.class));
    }

    @Test
    public void testCreateProductWithProductExistingUnprocessableException() {
        ProductCreateRequest productCreateRequest = getProductCreateRequest();

        when(productDatabase.exists(productCreateRequest.getSku())).thenReturn(true);

        assertThatThrownBy(() -> fixture.createProduct(productCreateRequest))
            .isInstanceOf(UnprocessableEntityException.class)
            .hasMessage("Product sku [43264] already exists");

        verify(productDatabase).exists(productCreateRequest.getSku());
        verifyNoMoreInteractions(productDatabase);
    }

    @Test
    public void testUpdateProductSuccess() {
        ProductUpdateRequest productUpdateRequest = getProductUpdateRequest(
            getInventoryRequest(asList(getWarehouseRequest(0), getWarehouseRequest(0))));

        when(productDatabase.exists(43264L)).thenReturn(true);
        when(productDatabase.updateProduct(any(Product.class)))
            .thenReturn(new Product(43264L, productUpdateRequest));

        ProductResponse productResponse = fixture.updateProduct(43264L, productUpdateRequest);

        assertThat(productResponse).isNotNull();
        assertThat(productResponse.getSku()).isEqualTo(43264L);
        assertThat(productResponse.getName()).isEqualTo(productUpdateRequest.getName());
        assertThat(productResponse.getInventory().getQuantity()).isZero();
        assertThat(productResponse.getInventory().isMarketable()).isFalse();
        assertThat(productResponse.getInventory().getWarehouses())
            .isNotNull()
            .isNotEmpty()
            .hasSize(2);

        verify(productDatabase).exists(43264L);
        verify(productDatabase).updateProduct(any(Product.class));
    }

    @Test
    public void testUpdateProductWithProductExistingUnprocessableException() {
        ProductUpdateRequest productUpdateRequest = getProductUpdateRequest();

        when(productDatabase.exists(43264L)).thenReturn(false);

        assertThatThrownBy(() -> fixture.updateProduct(43264L, productUpdateRequest))
            .isInstanceOf(UnprocessableEntityException.class)
            .hasMessage("Product sku [43264] not exists");

        verify(productDatabase).exists(43264L);
        verifyNoMoreInteractions(productDatabase);
    }

    @Test
    public void testGetProductSuccess() {
        when(productDatabase.getProduct(43264L))
            .thenReturn(Optional.of(getProduct("Name Test", getInventory(
                    asList(getWarehouse(10), getWarehouse(15), getWarehouse(5))))));

        ProductResponse productResponse = fixture.getProduct(43264L);

        assertThat(productResponse).isNotNull();
        assertThat(productResponse.getSku()).isEqualTo(43264L);
        assertThat(productResponse.getName()).isEqualTo("Name Test");
        assertThat(productResponse.getInventory().getWarehouses())
            .isNotNull()
            .isNotEmpty()
            .extracting(WarehouseResponse::getQuantity)
            .hasSize(3)
            .containsExactly(10, 15, 5);
        assertThat(productResponse.getInventory().getQuantity()).isEqualTo(30);
        assertThat(productResponse.getInventory().isMarketable()).isTrue();

        verify(productDatabase).getProduct(43264L);
        verifyNoMoreInteractions(productDatabase);
    }

    @Test
    public void testGetProductNotFoundException() {
        when(productDatabase.getProduct(43264L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> fixture.getProduct(43264L))
            .isInstanceOf(NotFoundException.class)
            .hasMessage("Product [43264] not found");

        verify(productDatabase).getProduct(43264L);
        verifyNoMoreInteractions(productDatabase);
    }

    @Test
    public void testDeleteProductSuccess() {
        doNothing().when(productDatabase).deleteProduct(43264L);

        fixture.deleteProduct(43264L);

        verify(productDatabase).deleteProduct(43264L);
        verifyNoMoreInteractions(productDatabase);
    }
}
