package br.com.blz.testjava.model.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.blz.testjava.model.entities.Inventory;
import br.com.blz.testjava.model.entities.Product;
import br.com.blz.testjava.model.entities.Warehouse;
import br.com.blz.testjava.model.entities.enums.ProductTypeEnum;
import br.com.blz.testjava.model.repository.ProductRepository;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class ProductRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("Should return true if already exist a product on database with SKU informed.")
    public void shouldReturnTrueIfSkuExist() {
        Long sku = 1L;
        Product product = createNewProduct();
        entityManager.persist(product);

        boolean exists = productRepository.existsBySku(sku);
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Should return false if doesn't exist a product on database with SKU informed.")
    public void shouldReturnFalseIfSkuDoesntExist() {
        Long sku = 1L;
        boolean exists = productRepository.existsBySku(sku);
        assertThat(exists).isFalse();
    }

    private Product createNewProduct() {
        Warehouse warehouse1 = Warehouse.builder().locality("SP").quantity(12)
            .type(ProductTypeEnum.ECOMMERCE).build();

        Warehouse warehouse2 = Warehouse.builder().locality("MOEMA").quantity(3)
            .type(ProductTypeEnum.PHYSICAL_STORE).build();

        Inventory inventory = Inventory.builder()
            .warehouses(Arrays.asList(warehouse1, warehouse2)).build();

        return Product.builder().name("L'Oréal Professionnel")
            .inventory(inventory).build();
    }

}
