package br.com.blz.testjava.product;

import br.com.blz.testjava.application.dto.inventory.InventoryForm;
import br.com.blz.testjava.application.dto.product.ProductDto;
import br.com.blz.testjava.application.dto.product.ProductForm;
import br.com.blz.testjava.application.dto.warehouse.WarehouseForm;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    private Product product;

    private Product productIsMarketableFalse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        WarehouseForm warehouseOne = new WarehouseForm("MOEMA",3,"PHYSICAL_STORE");
        WarehouseForm warehouseTwo = new WarehouseForm("SP",3,"ECOMMERCE");
        WarehouseForm warehouseOneMarketableFalse = new WarehouseForm("MOEMA",0,"PHYSICAL_STORE");
        WarehouseForm warehouseTwoMarketableFalse = new WarehouseForm("SP",0,"ECOMMERCE");
        InventoryForm inventory = new InventoryForm(Arrays.asList(warehouseOne,warehouseTwo));
        InventoryForm inventoryMarketableFalse = new InventoryForm(Arrays.asList(warehouseOneMarketableFalse,warehouseTwoMarketableFalse));
        product = new Product(43266L,"productTeste",inventory);
        productIsMarketableFalse = new Product(43267L,"productTesteMarketable",inventoryMarketableFalse);
    }

    @Test
    public void saveProduct() {
        Product productTeste = productServiceImpl.saveProduct(product);
        Assert.assertNotNull(productTeste);
        Assert.assertEquals(productTeste.getSku(),product.getSku());
        productServiceImpl.deleteProduct(productTeste.getSku());
    }

    @Test
    public void notSaveProductSameId() {
        Product productTeste = productServiceImpl.saveProduct(product);
        IllegalArgumentException thrown = Assert.assertThrows(IllegalArgumentException.class,
            ()-> {
                Product productError = productServiceImpl.saveProduct(product);
            });

        Assert.assertTrue(thrown.getMessage().contains("Ja existe produto cadastrado com o SKU: " + product.getSku()));
        productServiceImpl.deleteProduct(productTeste.getSku());
    }

    @Test
    void findBySkuProductInventoryQuantityGreaterThan0MarketableTrue() {
        Product productTeste = productServiceImpl.saveProduct(product);
        ProductDto productDto = productServiceImpl.findBySkuProduct(productTeste.getSku());

        Assert.assertNotNull(productDto);
        Assert.assertEquals(product.getSku(),productDto.getSku());
        Assert.assertTrue(productDto.getInventory().getQuantity() > 0);
        Assert.assertEquals(productDto.getMarketable(), true);
        productServiceImpl.deleteProduct(productTeste.getSku());
    }

    @Test
    void findBySkuProductInventoryQuantityEquals0MarketableFalse() {
        Product productTeste = productServiceImpl.saveProduct(productIsMarketableFalse);
        ProductDto productDto = productServiceImpl.findBySkuProduct(productTeste.getSku());

        Assert.assertNotNull(product);
        Assert.assertEquals(productTeste.getSku(),productDto.getSku());
        Assert.assertTrue(Objects.equals(productDto.getInventory().getQuantity(), 0));
        Assert.assertEquals(productDto.getMarketable(), false);

        productServiceImpl.deleteProduct(productTeste.getSku());
    }

    @Test
    void errorFindBySkuProductNotExist() {
        ResponseStatusException thrown = Assert.assertThrows(ResponseStatusException.class,
            ()->{
                ProductDto product = productServiceImpl.findBySkuProduct(54544L);
            });

        Assert.assertEquals(thrown.getStatus(), HttpStatus.NOT_FOUND);
    }

    @Test
    void findAllProducts() {
        Product productMarketAble = productServiceImpl.saveProduct(productIsMarketableFalse);
        Product productTeste = productServiceImpl.saveProduct(product);
        List<ProductDto> products = productServiceImpl.findAllProducts();

        Assert.assertEquals(products.size(), 2);
        Assert.assertFalse(products.isEmpty());
        productServiceImpl.deleteProduct(productMarketAble.getSku());
        productServiceImpl.deleteProduct(productTeste.getSku());
    }

    @Test
    void errorFindAllIsEmpty() {
        ResponseStatusException thrown = Assert.assertThrows(ResponseStatusException.class,
            ()->{
                List<ProductDto> product = productServiceImpl.findAllProducts();
            });

        Assert.assertEquals(thrown.getStatus(), HttpStatus.NOT_FOUND);
    }

    @Test
    void updateProduct() throws NoSuchFieldException {
        Product productTeste = productServiceImpl.saveProduct(product);
        ProductForm productform = new ProductForm(
            productTeste.getSku(),
            productIsMarketableFalse.getName(),
            productIsMarketableFalse.getInventory());
        Long productUpdate = productServiceImpl.updateProduct(product.getSku(),productform);

        Assert.assertEquals(productUpdate, product.getSku());
        Assert.assertNotEquals(product.getName(), productform.getName());

        productServiceImpl.deleteProduct(productTeste.getSku());

    }

    @Test
    void notUpdateProductIdNotExists() {
        ProductForm productform = new ProductForm(
            productIsMarketableFalse.getSku(),
            productIsMarketableFalse.getName(),
            productIsMarketableFalse.getInventory());

        ResponseStatusException thrown = Assert.assertThrows(ResponseStatusException.class,
            ()->{
                Long productUpdate = productServiceImpl.updateProduct(54544L,productform);
            });

        Assert.assertEquals(thrown.getStatus(), HttpStatus.NOT_FOUND);
        Assert.assertTrue(thrown.getMessage().contains("Nao ha produto cadastrado com o SKU: 54544"));

    }

    @Test
    void deleteProduct() {
            Product productTeste = productServiceImpl.saveProduct(product);
            productServiceImpl.deleteProduct(product.getSku());
        ResponseStatusException thrown = Assert.assertThrows(ResponseStatusException.class,
            ()-> {
                List<ProductDto> products = productServiceImpl.findAllProducts();
            });

        Assert.assertEquals(thrown.getStatus(), HttpStatus.NOT_FOUND);
        Assert.assertTrue(thrown.getMessage().contains("Nao ha produto cadastrado"));
    }

    @Test
    void notDeleteProductNotIdExist() {
        ResponseStatusException thrown = Assert.assertThrows(ResponseStatusException.class,
            ()-> {
                productServiceImpl.deleteProduct(product.getSku());
            });

        Assert.assertEquals(thrown.getStatus(), HttpStatus.NOT_FOUND);
        Assert.assertTrue(thrown.getMessage().contains("Nao ha produto cadastrado com o SKU: " + product.getSku()));
    }

}
