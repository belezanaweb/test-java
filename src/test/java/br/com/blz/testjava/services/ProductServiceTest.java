package br.com.blz.testjava.services;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.blz.testjava.model.entities.Product;
import br.com.blz.testjava.model.repository.ProductRepository;
import br.com.blz.testjava.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    ProductService productService;

    @MockBean
    ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    @DisplayName("Should save a product.")
    public void saveProductTest() {
        Product product = Product.builder().name("L'Oréal Professionnel").build();
        Mockito.when(productRepository.save(product))
            .thenReturn(Product.builder().sku(1L).name(product.getName()).build());

        Product savedProduct = productService.save(product);

        assertThat(savedProduct.getSku()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo(product.getName());
    }
}
