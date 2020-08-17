package br.com.blz.testjava.database;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static br.com.blz.testjava.mother.InventoryMother.getInventory;
import static br.com.blz.testjava.mother.ProductMother.getProduct;
import static br.com.blz.testjava.mother.WarehouseMother.getWarehouse;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductDatabaseTest {

    private ProductDatabase fixture;

    @Before
    public void setUp() {
        fixture = new ProductDatabase();
    }

    @Test
    public void testCreateProductAndGetProductSuccess() {
        Product product = fixture.createProduct(getProduct());

        Optional<Product> productOptional = fixture.getProduct(product.getSku());

        assertThat(productOptional.isPresent()).isTrue();
        Product product2 = productOptional.get();

        assertThat(product.getSku()).isEqualTo(product2.getSku());
        assertThat(product.getName()).isEqualTo(product2.getName());

        Integer quantity = product.getInventory().getWarehouses().get(0).getQuantity();
        Integer quantity2 = product.getInventory().getWarehouses().get(1).getQuantity();

        assertThat(product.getInventory().getWarehouses())
            .isNotNull()
            .isNotEmpty()
            .extracting(Warehouse::getQuantity)
            .hasSize(2).containsExactlyInAnyOrder(quantity, quantity2);
    }

    @Test
    public void testCreateProductWithProductExisting() {
        fixture.createProduct(getProduct());

        assertThatThrownBy(() -> fixture.createProduct(getProduct()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("The value is already in the base.");
    }

    @Test
    public void testUpdateProductAndGetProductSuccess() {
        fixture.createProduct(getProduct());
        Product product = fixture.updateProduct(getProduct("Name Test",
            getInventory(
                asList(getWarehouse(20), getWarehouse(21), getWarehouse(22)))));

        Optional<Product> productOptional = fixture.getProduct(product.getSku());

        assertThat(productOptional.isPresent()).isTrue();
        Product product2 = productOptional.get();

        assertThat(product.getSku()).isEqualTo(product2.getSku());
        assertThat(product.getName()).isEqualTo(product2.getName()).isEqualTo("Name Test");

        assertThat(product.getInventory().getWarehouses())
            .isNotNull()
            .isNotEmpty()
            .extracting(Warehouse::getQuantity)
            .hasSize(3).containsExactlyInAnyOrder(20, 21, 22);
    }

    @Test
    public void testUpdateProductWithProductNotFound() {
        assertThatThrownBy(() -> fixture.updateProduct(getProduct()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("The value not exists in the base.");
    }

    @Test
    public void testGetProductWithProductNotFound() {
        Optional<Product> product = fixture.getProduct(4521L);

        assertThat(product.isPresent()).isFalse();
    }

    @Test
    public void testDeleteProductSuccess() {
        Product product = fixture.createProduct(getProduct());

        fixture.deleteProduct(product.getSku());

        Optional<Product> productOptional = fixture.getProduct(product.getSku());
        assertThat(productOptional.isPresent()).isFalse();
    }

    @Test
    public void testDeleteProductWithProductNotFound() {
        Product product = fixture.createProduct(getProduct());

        fixture.deleteProduct(product.getSku());

        Optional<Product> productOptional = fixture.getProduct(product.getSku());
        assertThat(productOptional.isPresent()).isFalse();
    }

    @Test
    public void testExistsWithProductFound() {
        Product product = fixture.createProduct(getProduct());
        Boolean exists = fixture.exists(product.getSku());
        assertThat(exists).isTrue();
    }

    @Test
    public void testExistsWithProductNotFound() {
        Boolean exists = fixture.exists(getProduct().getSku());
        assertThat(exists).isFalse();
    }
}
