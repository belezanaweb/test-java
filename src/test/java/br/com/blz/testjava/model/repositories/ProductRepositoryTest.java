package br.com.blz.testjava.model.repositories;

import br.com.blz.testjava.model.entities.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository repository;

    @Test
    @DisplayName("Deve encontrar um produto por SKU")
    public void findBySkuTest() {
        Optional<Product> foundProduct = repository.findBySku(43264L);
        assertThat(foundProduct.isPresent()).isTrue();
    }
}
