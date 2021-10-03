package br.com.blz.testjava;


import br.com.blz.testjava.exception.ProductDuplicateException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestJavaApplicationTests {

    @Test
    @DisplayName("Deve obter um Produto por sku")
    void getProduct() {

        ProductService productService = new ProductService();
        ProductTestFactory productTestFactory = new ProductTestFactory();
        Product newProduct = productTestFactory.getProduct();

        productService.insert(newProduct);

        Product insertedProduct = productService.getBySku(newProduct.getSku());

        Assertions.assertEquals(insertedProduct.getSku(), newProduct.getSku());
    }


    @Test
    @DisplayName("Deve alterar um Produto por sku")
    void updateProduct() {

        ProductService productService = new ProductService();
        ProductTestFactory productTestFactory = new ProductTestFactory();
        Product newProduct = productTestFactory.getProduct();

        productService.insert(newProduct);

        Product product = productTestFactory.getProduct();
        Long newSku = 43265L;
        product.setSku(newSku);

        productService.update(product, newProduct.getSku());
        Product updatedProduct = productService.getBySku(newSku);
        Assertions.assertEquals(updatedProduct.getSku(), newSku);
    }

    @Test
    @DisplayName("Deve inserir um Produto")
    void insertProduct() {

        ProductService productService = new ProductService();
        ProductTestFactory productTestFactory = new ProductTestFactory();
        Product newProduct = productTestFactory.getProduct();

        productService.insert(newProduct);
        Product insertedProduct = productService.getBySku(newProduct.getSku());

        Assertions.assertEquals(newProduct.getSku(), insertedProduct.getSku());
    }


    @Test
    @DisplayName("Deve excluir um Produto por sku")
    void deleteProduct() {

        ProductService productService = new ProductService();
        ProductTestFactory productTestFactory = new ProductTestFactory();
        Product newProduct = productTestFactory.getProduct();

        productService.insert(newProduct);
        long sku = newProduct.getSku();

        productService.delete(sku);

        Assertions.assertThrows(ProductNotFoundException.class,
            ()-> productService.getBySku(sku));
    }

    @Test
    @DisplayName("Deve calcular a propriedade inventory.quantity")
    void calculateInventoryQuantity() {

        ProductService productService = new ProductService();
        ProductTestFactory productTestFactory = new ProductTestFactory();
        Product newProduct = productTestFactory.getProduct();
        Long expectedQuantity = 0L;
        List<Warehouse> warehouses = newProduct.getInventory().getWarehouses();

        warehouses.add(productTestFactory.getWarehouse());

        for(Warehouse warehouse: warehouses){
            expectedQuantity += warehouse.getQuantity();
        }

        productService.insert(newProduct);
        Product insertedProduct = productService.getBySku(newProduct.getSku());

        Assertions.assertEquals(expectedQuantity, insertedProduct.getInventory().getQuantity());
    }


    @Test
    @DisplayName("Deve calcular a propriedade isMarketable")
    void isMarketableProduct() {

        ProductService productService = new ProductService();
        ProductTestFactory productTestFactory = new ProductTestFactory();
        Product newProduct = productTestFactory.getProductZeroQuantity();

        productService.insert(newProduct);

        Product insertedProduct = productService.getBySku(newProduct.getSku());

        Assertions.assertFalse(insertedProduct.isMarketable());
    }

    @Test
    @DisplayName("Deve impedir a inserção um Produto com sku duplicado")
    void insertDuplicateProduct() {

        ProductService productService = new ProductService();
        ProductTestFactory productTestFactory = new ProductTestFactory();
        Product newProduct = productTestFactory.getProduct();

        productService.insert(newProduct);

        Assertions.assertThrows(ProductDuplicateException.class,
            ()-> productService.insert(newProduct));
    }

    @Test
    @DisplayName("Deve impedir a atualização de um Produto com sku duplicado")
    void updateDuplicateProduct() {

        ProductService productService = new ProductService();
        ProductTestFactory productTestFactory = new ProductTestFactory();
        Product newProductA = productTestFactory.getProduct();

        productService.insert(newProductA);

        Product newProductB = productTestFactory.getProduct();
        newProductB.setSku(43265L);

        productService.insert(newProductB);

        Product updatedProduct = productTestFactory.getProduct();
        updatedProduct.setSku(newProductB.getSku());

        Assertions.assertThrows(ProductDuplicateException.class,
            ()-> productService.update(updatedProduct, newProductA.getSku()));
    }
}
