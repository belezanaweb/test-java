package br.com.blz.testjava.infrastracture.product;

import br.com.blz.testjava.domain.product.Product;
import br.com.blz.testjava.domain.product.Warehouse;
import br.com.blz.testjava.infrastracture.exceptions.ConstraintViolationExceptionException;
import br.com.blz.testjava.infrastracture.product.persistence.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;

public class ProductDBGatewayTest {

    private ProductDbGateway productGateway;

    private ProductRepository productRepository;

    private static Product getNewProduct() {
        final var expectedName = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";
        return getNewProduct(expectedName);
    }

    private static Product getNewProduct(final String expectedName) {
        final var expectedSku = 43264L;
        final var expectedWarehouses = asList(Warehouse.newWarehouse("SP", 12, "ECOMMERCE"),
            Warehouse.newWarehouse("MOEMA", 3, "PHYSICAL_STORE"));
        return Product.newProduct(expectedSku, expectedName, expectedWarehouses);
    }

    @BeforeEach
    void setUp() {
        this.productRepository = new ProductRepository();
        this.productGateway = new ProductDbGateway(this.productRepository);
    }

    @Test
    public void givenAValidParam_whenCallsCreateProduct_shouldReturnProduct() {
        final Product newProduct = getNewProduct();
        Assertions.assertEquals(0, productRepository.count());
        final var product = productGateway.create(newProduct);
        Assertions.assertEquals(1, productRepository.count());

        Assertions.assertEquals(newProduct.getId(), product.getId());
    }

    @Test
    public void givenAValidParam_whenCallsCreateProduct_shouldReturnProductError() {
        final Product newProduct = getNewProduct();
        Assertions.assertEquals(0, productRepository.count());
        productGateway.create(newProduct);
        Assertions.assertEquals(1, productRepository.count());

        Assertions.assertThrows(ConstraintViolationExceptionException.class, () -> productGateway.create(newProduct), "Dois produtos são considerados iguais se os seus skus forem iguais");
    }

    @Test
    public void givenAValidParam_whenCallsGetProduct_shouldReturnProduct() {
        Assertions.assertEquals(0, productRepository.count());
        final var product = productGateway.create(getNewProduct());
        Assertions.assertEquals(1, productRepository.count());
        Product retrieveProduct = productGateway.findBySku(product.getId()).get();
        Assertions.assertEquals(retrieveProduct.getId(), product.getId());
        Assertions.assertEquals(retrieveProduct.getName(), product.getName());
    }

    @Test
    public void givenAValidParam_whenCallsUpdate_shouldReturnEditedProduct() {
        final var expectedName = "novo nome";
        Assertions.assertEquals(0, productRepository.count());
        final var oldProduct = productGateway.create(getNewProduct());
        Assertions.assertEquals(1, productRepository.count());
        Product editedProduct = productGateway.update(getNewProduct(expectedName));
        Assertions.assertEquals(editedProduct.getId(), oldProduct.getId());
        Assertions.assertEquals(editedProduct.getName(), expectedName);
        Assertions.assertNotEquals(editedProduct.getName(), oldProduct.getName());
    }
}
