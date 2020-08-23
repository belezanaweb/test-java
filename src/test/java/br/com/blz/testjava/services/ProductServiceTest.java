package br.com.blz.testjava.services;

import br.com.blz.testjava.utils.TestUtils;
import br.com.blz.testjava.dtos.ProductDTO;
import br.com.blz.testjava.exceptions.SkuAlreadyExistsException;
import br.com.blz.testjava.exceptions.SkuNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @BeforeEach
    void setUp() {
        try {
            productService.deleteProduct(12345L);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void create_NewProduct_Success() {
        ProductDTO productDTO = TestUtils.buildProduct();

        ProductDTO createdProduct = productService.createProduct(productDTO);

        assertThat(createdProduct).isEqualTo(productDTO);
    }

    @Test
    void create_DuplicatedProduct_SkuAlreadyExistsException() {
        ProductDTO productDTO = TestUtils.buildProduct();

        productService.createProduct(productDTO);

        Throwable thrown = catchThrowable(() -> productService.createProduct(productDTO));
        assertThat(thrown).isInstanceOf(SkuAlreadyExistsException.class);
    }

    @Test
    void update_ExistentProduct_Success() {
        ProductDTO productDTO = TestUtils.buildProduct();

        productService.createProduct(productDTO);
        productDTO.setName("Updated");
        ProductDTO updatedProduct = productService.updateProduct(productDTO.getSku(), productDTO);

        assertThat(updatedProduct.getName()).isEqualTo(productDTO.getName());
    }

    @Test
    void update_NonExistentProduct_ProductNotFoundException() {
        ProductDTO productDTO = TestUtils.buildProduct();

        Throwable thrown = catchThrowable(() -> productService.updateProduct(productDTO.getSku(), productDTO));
        assertThat(thrown).isInstanceOf(SkuNotFoundException.class);
    }

    @Test
    void get_ExistentProduct_Success() {
        ProductDTO productDTO = TestUtils.buildProduct();

        productService.createProduct(productDTO);
        ProductDTO productFound = productService.getProduct(productDTO.getSku());

        assertThat(productFound).isNotNull();
    }

    @Test
    void get_NonExistentProduct_ProductNotFoundException() {
        Throwable thrown = catchThrowable(() -> productService.getProduct(12345L));
        assertThat(thrown).isInstanceOf(SkuNotFoundException.class);
    }

    @Test
    void delete_ExistentProduct_Success() {
        ProductDTO productDTO = TestUtils.buildProduct();

        productService.createProduct(productDTO);
        productService.deleteProduct(productDTO.getSku());

        Throwable thrown = catchThrowable(() -> productService.getProduct(productDTO.getSku()));
        assertThat(thrown).isInstanceOf(SkuNotFoundException.class);
    }

    @Test
    void delete_NonExistentProduct_ProductNotFoundException() {
        Throwable thrown = catchThrowable(() -> productService.getProduct(123L));
        assertThat(thrown).isInstanceOf(SkuNotFoundException.class);
    }
}
