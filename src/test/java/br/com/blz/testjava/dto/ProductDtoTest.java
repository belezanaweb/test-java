package br.com.blz.testjava.dto;

import br.com.blz.testjava.domain.Product;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductDtoTest {

    @Test
    public void productDtoIsMarketableFalseTest() {
        Product product = new Product();
        product.setSku(123456L);
        product.setName("Sample 1");

        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setQuantity(0);

        ProductDto productDto = new ProductDto(product, inventoryDto);

        long skuExpected = 123456L;
        String nameExpected = "Sample 1";

        assertEquals("SKU não compatível", skuExpected, productDto.getSku().longValue());
        assertTrue("Falha ao montar o dna", nameExpected.equals(productDto.getName()));
        assertFalse("isMarketable incorreto", productDto.isMarketable());
    }

    @Test
    public void productDtoIsMarketableTrueTest() {
        Product product = new Product();
        product.setSku(123456L);
        product.setName("Sample 1");

        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setQuantity(1);

        ProductDto productDto = new ProductDto(product, inventoryDto);

        long skuExpected = 123456L;
        String nameExpected = "Sample 1";

        assertEquals("SKU não compatível", skuExpected, productDto.getSku().longValue());
        assertTrue("Falha ao montar o dna", nameExpected.equals(productDto.getName()));
        assertTrue("isMarketable incorreto", productDto.isMarketable());
    }

}
