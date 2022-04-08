package br.com.blz.testjava.application.controller.product;

import br.com.blz.testjava.application.dto.inventory.InventoryForm;
import br.com.blz.testjava.application.dto.product.ProductDto;
import br.com.blz.testjava.application.dto.product.ProductForm;
import br.com.blz.testjava.application.dto.warehouse.WarehouseForm;
import br.com.blz.testjava.product.Product;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    private ProductForm product;

    private ProductForm product2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        WarehouseForm warehouseOne = new WarehouseForm("MOEMA",3,"PHYSICAL_STORE");
        WarehouseForm warehouseTwo = new WarehouseForm("SP",3,"ECOMMERCE");
        WarehouseForm warehouseOneMarketableFalse = new WarehouseForm("MOEMA",0,"PHYSICAL_STORE");
        WarehouseForm warehouseTwoMarketableFalse = new WarehouseForm("SP",0,"ECOMMERCE");
        InventoryForm inventory = new InventoryForm(Arrays.asList(warehouseOne,warehouseTwo));
        InventoryForm inventoryMarketableFalse = new InventoryForm(Arrays.asList(warehouseOneMarketableFalse,warehouseTwoMarketableFalse));
        product = new ProductForm(43266L,"productTeste",inventory);
        product2 = new ProductForm(43267L,"productTesteMarketable",inventoryMarketableFalse);
    }

    @Test
    void createProduct() throws NoSuchFieldException {
        ResponseEntity<Product> productTeste = productController.createProduct(product);
        Assert.assertNotNull(productTeste);
        Assert.assertEquals(productTeste.getStatusCode(), HttpStatus.CREATED);
        Assert.assertEquals(productTeste.getBody().getSku(),product.getSku());
        productController.deleteProduct(productTeste.getBody().getSku());
    }

    @Test
    void notCreateProductFormProductError() throws NoSuchFieldException {
        ProductForm productForm = new ProductForm(null,"teste", product.getInventory());
        NoSuchFieldException thrown = Assert.assertThrows(NoSuchFieldException.class,
            ()-> {
                ResponseEntity<Product> productTeste = productController.createProduct(productForm);
            });
        Assert.assertEquals(thrown.getMessage(),"Campos sku, name e inventory nao podem ser nulos ou vazios");
    }

    @Test
    void notCreateProductFormInventoryError() throws NoSuchFieldException {
        List<WarehouseForm> warehouseForms = Arrays.asList();
        InventoryForm inventoryForm = new InventoryForm(warehouseForms);
        ProductForm productForm = new ProductForm(45690L,"teste", inventoryForm);
        NoSuchFieldException thrown = Assert.assertThrows(NoSuchFieldException.class,
            ()-> {
                ResponseEntity<Product> productTeste = productController.createProduct(productForm);
            });
        Assert.assertEquals(thrown.getMessage(),"Warehouses nao pode ser null ou com lista vazia");
    }

    @Test
    void notCreateProductFormWarehouseError() throws NoSuchFieldException {
        WarehouseForm warehouseOne = new WarehouseForm("MOEMA",-1,"PHYSICAL_STORE");
        List<WarehouseForm> warehouseForms = Arrays.asList(warehouseOne);
        InventoryForm inventoryForm = new InventoryForm(warehouseForms);
        ProductForm productForm = new ProductForm(45660L,"teste", inventoryForm);
        NoSuchFieldException thrown = Assert.assertThrows(NoSuchFieldException.class,
            ()-> {
                ResponseEntity<Product> productTeste = productController.createProduct(productForm);
            });
        Assert.assertEquals(thrown.getMessage(),"Campos locality,quantity e type nao podem ser nulos ou vazios e quantity tem que ser positivo");
    }

    @Test
    void findAllProducts() throws NoSuchFieldException {
        productController.createProduct(product);
        ResponseEntity<List<ProductDto>> products = productController.findAllProducts();
        Assert.assertNotNull(products);
        Assert.assertEquals(products.getBody().size(), 1);
        Assert.assertEquals(products.getStatusCode(), HttpStatus.OK);
        productController.deleteProduct(product.getSku());
    }

    @Test
    void errorFindAllIsEmpty() {
        ResponseStatusException thrown = Assert.assertThrows(ResponseStatusException.class,
            ()->{
                ResponseEntity<List<ProductDto>> products = productController.findAllProducts();
            });

        Assert.assertEquals(thrown.getStatus(), HttpStatus.NOT_FOUND);
    }

    @Test
    void findProductsBySku() throws NoSuchFieldException {
        ResponseEntity<Product> productCreated = productController.createProduct(product);
        ResponseEntity<ProductDto> productTeste = productController.findProductsBySku(productCreated.getBody().getSku());

        Assert.assertNotNull(productTeste);
        Assert.assertEquals(productTeste.getBody().getSku(), product.getSku());
        Assert.assertEquals(productTeste.getStatusCode(), HttpStatus.OK);
        Assert.assertTrue(productTeste.getBody().getInventory().getQuantity() > 0);
        Assert.assertEquals(productTeste.getBody().getMarketable(), true);
        productController.deleteProduct(productCreated.getBody().getSku());
    }

    @Test
    void findBySkuProductInventoryQuantityEquals0MarketableFalse() throws NoSuchFieldException {
        ResponseEntity<Product> productTeste = productController.createProduct(product2);
        ResponseEntity<ProductDto> productDto = productController.findProductsBySku(productTeste.getBody().getSku());

        Assert.assertNotNull(product);
        Assert.assertEquals(productDto.getBody().getSku(), productTeste.getBody().getSku());
        Assert.assertEquals(productDto.getStatusCode(), HttpStatus.OK);
        Assert.assertTrue(Objects.equals(productDto.getBody().getInventory().getQuantity(), 0));
        Assert.assertEquals(productDto.getBody().getMarketable(), false);

        productController.deleteProduct(productTeste.getBody().getSku());
    }

    @Test
    void errorFindBySkuProductNotExist() {
        ResponseStatusException thrown = Assert.assertThrows(ResponseStatusException.class,
            ()->{
                ResponseEntity<ProductDto> product = productController.findProductsBySku(54544L);
            });

        Assert.assertEquals(thrown.getStatus(), HttpStatus.NOT_FOUND);
    }

    @Test
    void updateProduct() throws NoSuchFieldException {
        ProductForm productForm = new ProductForm(product.getSku(), product2.getName(), product2.getInventory());
        productController.createProduct(product);
        ResponseEntity<Long> productTesteUpdate = productController.updateProduct(product.getSku(), productForm);
        Assert.assertNotNull(productTesteUpdate);
        Assert.assertEquals(productTesteUpdate.getBody(), product.getSku());
        Assert.assertEquals(productTesteUpdate.getStatusCode(), HttpStatus.OK);
        productController.deleteProduct(product.getSku());
    }

    @Test
    void notUpdateProductIdNotExists() {
        ResponseStatusException thrown = Assert.assertThrows(ResponseStatusException.class,
            ()->{
                ResponseEntity<Long> productUpdate = productController.updateProduct(54544L,product);
            });

        Assert.assertEquals(thrown.getStatus(), HttpStatus.NOT_FOUND);
        Assert.assertTrue(thrown.getMessage().contains("Nao ha produto cadastrado com o SKU: 54544"));

    }

    @Test
    void deleteProduct() throws NoSuchFieldException {
        ResponseEntity<Product> productTeste = productController.createProduct(product);
        productController.deleteProduct(productTeste.getBody().getSku());
        ResponseStatusException thrown = Assert.assertThrows(ResponseStatusException.class,
            ()-> {
                ResponseEntity<List<ProductDto>> products = productController.findAllProducts();
            });

        Assert.assertEquals(thrown.getStatus(), HttpStatus.NOT_FOUND);
        Assert.assertTrue(thrown.getMessage().contains("Nao ha produto cadastrado"));
    }

    @Test
    void notDeleteProductNotIdExist() {
        ResponseStatusException thrown = Assert.assertThrows(ResponseStatusException.class,
            ()-> {
                productController.deleteProduct(product.getSku());
            });

        Assert.assertEquals(thrown.getStatus(), HttpStatus.NOT_FOUND);
        Assert.assertTrue(thrown.getMessage().contains("Nao ha produto cadastrado com o SKU: " + product.getSku()));
    }
}
