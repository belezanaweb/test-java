package br.com.blz.testjava.domain;

import br.com.blz.testjava.dto.ProductDto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductTest {

    @Test
    public void createProductTest() {
        Product product = new Product();
        product.setSku(123456L);
        product.setName("Sample 1");

        long skuExpected = 123456L;
        String nameExpected = "Sample 1";

        assertEquals("SKU não compatível", skuExpected, product.getSku().longValue());
        assertTrue("Falha ao montar o dna", nameExpected.equals(product.getName()));
    }

    @Test
    public void createProductByProductDtoTest() {
        ProductDto productDto = new ProductDto();
        productDto.setSku(123456L);
        productDto.setName("Sample 1");

        Product product = new Product(productDto);

        long skuExpected = 123456L;
        String nameExpected = "Sample 1";

        assertEquals("SKU não compatível", skuExpected, product.getSku().longValue());
        assertTrue("Falha ao montar o dna", nameExpected.equals(product.getName()));
    }

}
