package br.com.blz.testjava.services;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.blz.testjava.exceptions.BusinessException;
import br.com.blz.testjava.model.entities.Product;
import br.com.blz.testjava.model.repository.ProductRepository;
import br.com.blz.testjava.services.impl.ProductServiceImpl;
import org.assertj.core.api.Assertions;
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
        Product product = createNewProduct();
        Mockito.when(productRepository.existsBySku(Mockito.anyLong())).thenReturn(false);
        Mockito.when(productRepository.save(product))
            .thenReturn(Product.builder().sku(1L).name(product.getName()).build());

        Product savedProduct = productService.save(product);

        assertThat(savedProduct.getSku()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo(product.getName());
    }

    @Test
    @DisplayName("Should throw a business error to try saving a product with SKU duplicated")
    public void shouldNotSaveWithDuplicatedSKU() {
        Product product = createNewProduct();
        Mockito.when(productRepository.existsBySku(Mockito.anyLong())).thenReturn(true);

        Throwable exception = Assertions.catchThrowable(() -> productService.save(product));
        assertThat(exception)
            .isInstanceOf(BusinessException.class)
            .hasMessage("SKU already used by other product.");

        Mockito.verify(productRepository, Mockito.never()).save(product);
    }

    private Product createNewProduct() {
        return Product.builder().sku(1L).name("L'Oréal Professionnel").build();
    }
}
