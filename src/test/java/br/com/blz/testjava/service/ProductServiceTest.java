package br.com.blz.testjava.service;


import br.com.blz.testjava.exception.ResourceAlreadyExistException;
import br.com.blz.testjava.exception.ResourceNotFoundException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.model.WarehouseType;
import br.com.blz.testjava.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(JUnit4.class)
public class ProductServiceTest {

    private ProductService productService = null;

    private ProductRepository productRepository = null;

    @Before
    public void beforeTest() {
        productRepository = new ProductRepository();
        productService = new ProductService(productRepository);
    }

    // add product tests
    @Test
    public void shouldAddProductWithSuccess() {
        // given
        final Product mockedProduct = getMockProduct();

        // when
        final Product savedProduct = productService.add(mockedProduct);

        // then
        baseAssertEquals(mockedProduct, savedProduct);
        baseAssertEqualsWarehouseList(mockedProduct.getInventory().getWarehouses(),
            savedProduct.getInventory().getWarehouses());

        Assert.assertFalse(savedProduct.isMarketable());
        Assert.assertEquals(0, savedProduct.getInventory().getQuantity());
    }

    @Test(expected = ResourceAlreadyExistException.class)
    public void shouldTryToAddProductAndFailDueProductAlreadyExistent() {
        // given
        final Product mockedProduct = getMockProduct();

        productService.add(mockedProduct);

        final Product mockedProductDouble = getMockProduct();

        // when
        productService.add(mockedProductDouble);
    }

    // get product tests
    @Test
    public void shouldAddAndGetProductWithSuccess() {
        // given
        final Product mockedProduct = getMockProduct();

        final Product savedProduct = productService.add(mockedProduct);

        // when
        final Product getProduct = productService.get(mockedProduct.getSku());

        // then
        baseAssertEquals(savedProduct, getProduct);
        baseAssertEqualsWarehouseList(savedProduct.getInventory().getWarehouses(),
            getProduct.getInventory().getWarehouses());

        Assert.assertTrue(savedProduct.isMarketable());
        Assert.assertEquals(20, savedProduct.getInventory().getQuantity());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldTryToAddAndGetProductAndFailDueResourceNotFound() {
        // given
        final Product mockedProduct = getMockProduct();

        final Product savedProduct = productService.add(mockedProduct);

        // when
        productService.get(1005);
    }

    // update product tests
    @Test
    public void shouldAddAndUpdateProductWithSuccess() {
        // given
        final Product mockedProduct = getMockProduct();
        productService.add(mockedProduct);
        final Product savedProduct = productService.get(mockedProduct.getSku());

        final Product mockedProductDouble = getMockProduct();
        mockedProductDouble.setName("double trouble");
        mockedProductDouble.getInventory().getWarehouses().get(0).setType(WarehouseType.PHYSICAL_STORE);
        mockedProductDouble.getInventory().getWarehouses().get(0).setQuantity(30);

        // when
        final Product savedDouble = productService.update(mockedProductDouble);

        // then
        Assert.assertNotEquals(savedProduct.getName(), savedDouble.getName());
        Assert.assertEquals("double trouble", savedDouble.getName());
        Assert.assertNotEquals(
            savedProduct.getInventory().getWarehouses().get(0).getType(),
            savedDouble.getInventory().getWarehouses().get(0).getType());
        Assert.assertEquals(WarehouseType.PHYSICAL_STORE, savedDouble.getInventory().getWarehouses().get(0).getType());
        Assert.assertNotEquals(
            savedProduct.getInventory().getWarehouses().get(0).getQuantity(),
            savedDouble.getInventory().getWarehouses().get(0).getQuantity());
        Assert.assertEquals(30, savedDouble.getInventory().getWarehouses().get(0).getQuantity());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldTryToAddAndUpdateProductAndFailDueResourceNotFound() {
        // given
        final Product mockedProduct = getMockProduct();
        productService.add(mockedProduct);
        productService.get(mockedProduct.getSku());

        final Product mockedProductDouble = getMockProduct();
        mockedProductDouble.setSku(10005);

        // when
        productService.update(mockedProductDouble);

        // then
    }

    // delete product tests
    @Test
    public void shouldDeleteProductWithSuccess() {
        // given
        final Product mockedProduct = getMockProduct();
        final Product savedProduct = productService.add(mockedProduct);
        final Product storedProduct = productService.get(savedProduct.getSku());

        // when
        productService.delete(storedProduct.getSku());

        // then
        final Optional<Product> repositoryProduct = productRepository.findById(storedProduct.getSku());
        Assert.assertFalse(repositoryProduct.isPresent());
    }

    // delete product tests
    @Test(expected = ResourceNotFoundException.class)
    public void shouldTryToDeleteProductWithSuccess() {
        // given
        final Product mockedProduct = getMockProduct();
        final Product savedProduct = productService.add(mockedProduct);
        final Product storedProduct = productService.get(savedProduct.getSku());

        // when
        productService.delete(storedProduct.getSku());

        // then
        productService.get(storedProduct.getSku());
    }

    // helper methods
    private void baseAssertEquals(final Product expected, final Product actual) {
        Assert.assertEquals(expected.getSku(), actual.getSku());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertNotNull(expected.getInventory());
        Assert.assertEquals(expected.getInventory().getQuantity(), actual.getInventory().getQuantity());
        Assert.assertEquals(expected.getInventory().getWarehouses().size(), actual.getInventory().getWarehouses().size());
    }

    private void baseAssertEqualsWarehouseList(final List<Warehouse> expected, final List<Warehouse> actual) {
        for (final Warehouse expectedWarehouse : expected) {
            final Warehouse savedWareHouse = actual
                .stream().filter(warehouse ->
                    expectedWarehouse.getQuantity() == warehouse.getQuantity() &&
                        expectedWarehouse.getLocality().equals(warehouse.getLocality()) &&
                        expectedWarehouse.getType().equals(warehouse.getType()))
                .findAny().get();
            Assert.assertNotNull(savedWareHouse);
        }
    }

    private Product getMockProduct() {
        final Warehouse warehouse = new Warehouse();
        warehouse.setLocality("MOEMA");
        warehouse.setQuantity(20);
        warehouse.setType(WarehouseType.ECOMMERCE);

        final Inventory inventory = new Inventory();
        inventory.setQuantity(15);
        inventory.setWarehouses(new ArrayList<>());
        inventory.getWarehouses().add(warehouse);

        final Product product = new Product();
        product.setMarketable(true);
        product.setInventory(inventory);
        product.setName("test1");
        product.setSku(12L);

        return product;
    }
}
