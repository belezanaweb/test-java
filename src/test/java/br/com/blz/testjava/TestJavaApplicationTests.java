package br.com.blz.testjava;

import br.com.blz.testjava.enums.WarehouseType;
import br.com.blz.testjava.exceptionhandler.exception.ProductAlreadyExistException;
import br.com.blz.testjava.mock.Mocks;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJavaApplicationTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    static Product product;
    static Long sku = 43264L;

    @BeforeClass
    static public void setup() {
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(Mocks.createWarehouse("SP", WarehouseType.ECOMMERCE, 12L));
        Inventory inventory = Mocks.createInventory(warehouses);
        product = Mocks.createProduct(sku, "Expert Absolut", inventory);
    }

    @Before
    public void clean() {
        this.productRepository.deleteAll();
    }

    @Test
    public void saveProductAndSkuIsEqual43264() {
        this.productService.save(product);

        Product product = this.productService.findBySku(43264L);

        Assertions.assertEquals((long)sku, (long)product.getSku());
    }

    @Test
    public void saveProductAndMarkTableIsTrueAndQuantityIs12() {
        this.productService.save(product);

        Product product = this.productService.findBySku(43264L);

        Assertions.assertAll(
            () -> Assertions.assertTrue(product.getMarketable()),
            () -> Assertions.assertEquals((long)12L, (long)product.getInventory().getQuantity())
        );
    }

    @Test
    public void  whenSaveTwoProductsWithSaveSkuThrowProductAlreadyExistException() {
        this.productService.save(product);

        Assertions.assertThrows(ProductAlreadyExistException.class, () -> this.productService.save(product));
    }

    @Test
    public void updateProduct() {
        String newName = "Test";

        this.productService.save(product);

        product.setName(newName);
        this.productService.updateBySku(sku, product);

        Product product = this.productService.findBySku(sku);

        Assertions.assertEquals(product.getName(), newName);
    }

    @Test
    public void deleteAProductBySkull() {
        this.productService.save(product);

        this.productService.deleteBySku(sku);
    }
}
