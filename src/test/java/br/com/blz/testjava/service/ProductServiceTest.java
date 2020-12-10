package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.exception.EntityAlreadyExistsException;
import br.com.blz.testjava.exception.EntityNotFoundException;
import br.com.blz.testjava.mock.ProductMock;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    private Product product = ProductMock.create("product1", 567765, 14, "SP", Warehouse.Type.PHYSICAL_STORE);

    @Test
    public void findBySkuTest(){
        product.setSku(533454L);
        productService.save(product);
        final Product saved = productService.findBySku(product.getSku());
        assertNotNull(saved);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findBySkuNotFoundTest(){
         productService.findBySku(4234234L);
    }

    @Test
    public void createTest(){
        Product newProduct = ProductMock.create("product1", 123908, 14, "SP", Warehouse.Type.PHYSICAL_STORE);

        final Product saved = productService.save(newProduct);

        assertNotNull(saved.getId());
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void createDuplicatedTest(){
        productService.save(product);
        productService.save(product);
    }

    @Test
    public void deleteTest(){
        Product newProduct = ProductMock.create("product1", 1, 14, "SP", Warehouse.Type.PHYSICAL_STORE);
        productService.save(newProduct);
        productService.delete(newProduct.getSku());
    }

    @Test
    public void calculationQuantityTest(){
        Product p1 = ProductMock.create("product1", 11, 14, "SP", Warehouse.Type.PHYSICAL_STORE);
        p1.getInventory().setWarehouses(Arrays.asList(new Warehouse("RJ",2, Warehouse.Type.ECOMMERCE),
                                                      new Warehouse("MG",5, Warehouse.Type.ECOMMERCE),
                                                      new Warehouse("MG",20, Warehouse.Type.ECOMMERCE)));
        productService.save(p1);
        final Product saved = productService.findBySku(p1.getSku());
        assertEquals(Integer.valueOf(27), saved.getInventory().getQuantity());
        assertTrue(saved.isMarketable());
    }
}
