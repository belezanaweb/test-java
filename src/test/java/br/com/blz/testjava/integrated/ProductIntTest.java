package br.com.blz.testjava.integrated;

import br.com.blz.testjava.controller.ProductController;
import br.com.blz.testjava.controller.resources.ProductRequest;
import br.com.blz.testjava.controller.resources.ProductResponse;
import br.com.blz.testjava.exception.NotFoundException;
import br.com.blz.testjava.exception.SkuAlreadyRegisteredException;
import br.com.blz.testjava.persistence.repository.ProductRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static br.com.blz.testjava.mother.ProductMother.createProductRequest;
import static br.com.blz.testjava.mother.ProductMother.createProductRequestWithTwoWarehouses;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductIntTest {

    @Autowired
    private ProductController fixture;

    @Autowired
    private ProductRepository productRepository;

    @After
    public void after() {
        productRepository.deleteAll();
    }

    @Test
    @Rollback
    public void testCreate() {
        ProductRequest productRequest = createProductRequest();
        fixture.createProduct(createProductRequest());

        ProductResponse result = fixture.getProduct(productRequest.getSku()).getBody();

        assertThat(result).isNotNull();
        assertThat(result.getInventoryResponse().getWarehousesResponse().size()).isEqualTo(productRequest.getInventory().getWarehouse().size());
        assertThat(result.getInventoryResponse().getQuantity()).isEqualTo(10);
        assertThat(result.getMarketable()).isTrue();

        ProductRequest product2 = createProductRequestWithTwoWarehouses();
        product2.setSku(321L);
        fixture.createProduct(product2);

        ProductResponse result2 = fixture.getProduct(product2.getSku()).getBody();
        assertThat(result2).isNotNull();
        assertThat(result2.getInventoryResponse().getWarehousesResponse().size()).isEqualTo(product2.getInventory().getWarehouse().size());
        assertThat(result2.getInventoryResponse().getQuantity()).isEqualTo(20);
        assertThat(result2.getMarketable()).isTrue();
    }

    @Test
    @Rollback
    public void testCreateProductAlreadyExists() {
        ProductRequest productRequest = createProductRequest();
        fixture.createProduct(createProductRequest());

        ProductResponse result = fixture.getProduct(productRequest.getSku()).getBody();

        assertThat(result).isNotNull();
        assertThat(result.getInventoryResponse().getWarehousesResponse().size()).isEqualTo(productRequest.getInventory().getWarehouse().size());
        assertThat(result.getInventoryResponse().getQuantity()).isEqualTo(10);
        assertThat(result.getMarketable()).isTrue();

        ProductRequest product2 = createProductRequestWithTwoWarehouses();

        try {
            fixture.createProduct(product2);
            fail("Should throw a SkuAlreadyRegisteredException");
        } catch (SkuAlreadyRegisteredException e) {
            assertThat(e.getMessage()).isEqualTo("Product with sku 123 already exists!");
        }

    }

    @Test
    @Rollback
    public void testUpdateProduct() {
        ProductRequest productRequest = createProductRequest();
        fixture.createProduct(createProductRequest());

        productRequest.setName("Another name!");

        ResponseEntity<ProductResponse> result = fixture.updateProduct(productRequest);

        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getName()).isEqualTo(productRequest.getName());
    }

    @Test
    @Rollback
    public void testDeleteProduct() {
        ProductRequest productRequest = createProductRequest();
        fixture.createProduct(createProductRequest());

        fixture.deleteProduct(productRequest.getSku());

        try {
            fixture.getProduct(productRequest.getSku());
        } catch (NotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Product not found.");
        }
    }

    @Test
    @Rollback
    public void testDeleteProductNotFound() {
        try {
            fixture.deleteProduct(123L);
        } catch (NotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Product not found!");
        }
    }

}
