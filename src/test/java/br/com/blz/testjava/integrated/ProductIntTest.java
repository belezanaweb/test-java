package br.com.blz.testjava.integrated;

import br.com.blz.testjava.controller.ProductController;
import br.com.blz.testjava.database.ProductDatabase;
import br.com.blz.testjava.domain.request.ProductCreateRequest;
import br.com.blz.testjava.domain.request.ProductUpdateRequest;
import br.com.blz.testjava.domain.response.ProductResponse;
import br.com.blz.testjava.domain.response.WarehouseResponse;
import br.com.blz.testjava.exception.NotFoundException;
import br.com.blz.testjava.exception.UnprocessableEntityException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import static br.com.blz.testjava.mother.ProductMother.getProductCreateRequest;
import static br.com.blz.testjava.mother.ProductMother.getProductUpdateRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductIntTest {

    @Autowired
    private ProductController controller;

    @Autowired
    private ProductDatabase productDatabase;

    @Before
    public void setUp() {
        productDatabase.clearBase();
    }

    @Test
    public void testCreateProductSuccess() {
        ProductCreateRequest productCreateRequest = getProductCreateRequest();
        ResponseEntity<ProductResponse> responseEntity = controller
            .createProduct(productCreateRequest, UriComponentsBuilder.newInstance());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getSku()).isEqualTo(43264L);
        assertThat(responseEntity.getBody().getName()).isEqualTo(productCreateRequest.getName());
        assertThat(responseEntity.getBody().getInventory().getQuantity()).isEqualTo(20);
        assertThat(responseEntity.getBody().getInventory().getWarehouses())
            .isNotNull()
            .isNotEmpty()
            .extracting(WarehouseResponse::getQuantity)
            .hasSize(2)
            .containsExactly(10, 10);
        assertThat(responseEntity.getBody().getInventory().isMarketable()).isTrue();
    }

    @Test
    public void testCreateProductAlreadyExisting() {
        ProductCreateRequest productCreateRequest = getProductCreateRequest();
        controller.createProduct(productCreateRequest, UriComponentsBuilder.newInstance());

        assertThatThrownBy(() -> controller
            .createProduct(productCreateRequest, UriComponentsBuilder.newInstance()))
            .isInstanceOf(UnprocessableEntityException.class)
            .hasMessage("Product sku [43264] already exists");
    }

    @Test
    public void testUpdateProductSuccess() {
        ProductCreateRequest productCreateRequest = getProductCreateRequest();
        ResponseEntity<ProductResponse> responseEntity = controller
            .createProduct(productCreateRequest, UriComponentsBuilder.newInstance());

        ProductUpdateRequest productUpdateRequest = getProductUpdateRequest();
        ResponseEntity<ProductResponse> updateResponseEntity = controller
            .updateProduct(43264L, productUpdateRequest);

        assertThat(updateResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updateResponseEntity.getBody().getSku()).isEqualTo(43264L);
        assertThat(updateResponseEntity.getBody().getName()).isEqualTo("Name Update");
        assertThat(responseEntity.getBody().getInventory().getQuantity()).isEqualTo(20);
        assertThat(responseEntity.getBody().getInventory().getWarehouses())
            .isNotNull()
            .isNotEmpty()
            .extracting(WarehouseResponse::getQuantity)
            .hasSize(2)
            .containsExactly(10, 10);
        assertThat(responseEntity.getBody().getInventory().isMarketable()).isTrue();

        ProductResponse productResponse = controller.getProduct(43264L).getBody();

        assertThat(productResponse.getName())
            .isEqualTo(productUpdateRequest.getName())
            .isNotEqualTo(productCreateRequest.getName());
    }

    @Test
    public void testUpdateProductNotExists() {
        ProductUpdateRequest productUpdateRequest = getProductUpdateRequest();

        assertThatThrownBy(() -> controller.updateProduct(43264L, productUpdateRequest))
            .isInstanceOf(UnprocessableEntityException.class)
            .hasMessage("Product sku [43264] not exists");
    }

    @Test
    public void testGetProductSuccess() {
        ProductCreateRequest productCreateRequest = getProductCreateRequest();
        controller.createProduct(productCreateRequest, UriComponentsBuilder.newInstance());

        ResponseEntity<ProductResponse> responseEntity = controller
            .getProduct(productCreateRequest.getSku());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getSku()).isEqualTo(43264L);
        assertThat(responseEntity.getBody().getName()).isEqualTo(productCreateRequest.getName());
        assertThat(responseEntity.getBody().getInventory().getQuantity()).isEqualTo(20);
        assertThat(responseEntity.getBody().getInventory().getWarehouses())
            .isNotNull()
            .isNotEmpty()
            .extracting(WarehouseResponse::getQuantity)
            .hasSize(2)
            .containsExactly(10, 10);
        assertThat(responseEntity.getBody().getInventory().isMarketable()).isTrue();
    }

    @Test
    public void testGetProductNotFound() {
        assertThatThrownBy(() -> controller.getProduct(43264L))
            .isInstanceOf(NotFoundException.class)
            .hasMessage("Product [43264] not found");
    }

    @Test
    public void testDeleteProductSuccess() {
        ProductCreateRequest productCreateRequest = getProductCreateRequest();
        controller.createProduct(productCreateRequest, UriComponentsBuilder.newInstance());

        controller.deleteProduct(43264L);

        assertThatThrownBy(() -> controller.getProduct(43264L))
            .isInstanceOf(NotFoundException.class)
            .hasMessage("Product [43264] not found");
    }

    @Test
    public void testDeleteProductNotFoundSuccess() {
        assertThatThrownBy(() -> controller.getProduct(43264L))
            .isInstanceOf(NotFoundException.class)
            .hasMessage("Product [43264] not found");

        controller.deleteProduct(43264L);
    }
}
