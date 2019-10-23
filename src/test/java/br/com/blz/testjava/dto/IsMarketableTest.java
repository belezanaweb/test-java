package br.com.blz.testjava.dto;

import br.com.blz.testjava.controller.dto.ProductDTO;
import br.com.blz.testjava.service.MockService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IsMarketableTest {
    @Autowired private MockService mockService;

    @Test
    public void validaIsMarketable() {
        final ProductDTO product = mockService.createProduct();
        assertTrue(product.getIsMarketable());
    }

    @Test
    public void validateIsNotMarketable() {
        final ProductDTO product = new ProductDTO();
        Assert.assertFalse(product.getIsMarketable());
    }
}
