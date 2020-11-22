package br.com.blz.testjava.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.blz.testjava.exceptions.BusinessException;
import br.com.blz.testjava.model.entities.Inventory;
import br.com.blz.testjava.model.entities.Product;
import br.com.blz.testjava.model.entities.Warehouse;
import br.com.blz.testjava.model.entities.enums.ProductTypeEnum;
import br.com.blz.testjava.model.repository.ProductRepository;
import br.com.blz.testjava.services.impl.ProductServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    ProductService productService;

    @MockBean
    ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    @DisplayName("Should save a product.")
    public void saveProductTest() {
        Product product = createNewProduct();
        Mockito.when(productRepository.existsBySku(Mockito.anyLong())).thenReturn(false);
        Mockito.when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.save(product);

        assertThat(savedProduct.getSku()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo(product.getName());
    }

    @Test
    @DisplayName("Should throw a business error to try saving a product with SKU duplicated")
    public void shouldNotSaveWithDuplicatedSKU() {
        Product product = createNewProduct();
        Mockito.when(productRepository.existsBySku(Mockito.anyLong())).thenReturn(true);

        Throwable exception = Assertions.catchThrowable(() -> productService.save(product));
        assertThat(exception)
            .isInstanceOf(BusinessException.class)
            .hasMessage("SKU already used by other product.");

        Mockito.verify(productRepository, Mockito.never()).save(product);
    }

    @Test
    @DisplayName("Should get a product by sku")
    public void getProductBySkuTest() {
        Long sku = 1L;
        Product product = createNewProduct();
        product.setSku(sku);
        Mockito.when(productRepository.findBySku(sku)).thenReturn(Optional.of(product));

        Optional<Product> productFound = productService.getProductBySku(sku);

        assertThat(productFound.isPresent()).isTrue();
        assertThat(productFound.get().getSku()).isEqualTo(sku);
        assertThat(productFound.get().getName()).isEqualTo(product.getName());
        assertThat(productFound.get().getInventory()).isNotNull();
        assertThat(productFound.get().getInventory().getWarehouses()).isNotNull();

        List<Warehouse> warehousesFounded = productFound.get().getInventory().getWarehouses();
        List<Warehouse> warehousesMocked = product.getInventory().getWarehouses();

        for (int i = 0; i < warehousesFounded.size(); i++) {
            assertThat(warehousesFounded.get(i).getQuantity())
                .isEqualTo(warehousesMocked.get(i).getQuantity());
            assertThat(warehousesFounded.get(i).getLocality())
                .isEqualTo(warehousesMocked.get(i).getLocality());
            assertThat(warehousesFounded.get(i).getType())
                .isEqualTo(warehousesMocked.get(i).getType());
        }
    }

    @Test
    @DisplayName("Should return empty if the product with SKU informed doesn't exist on database")
    public void productNotFoundBySkuTest() {
        Long sku = 1L;
        Mockito.when(productRepository.findBySku(sku)).thenReturn(Optional.empty());

        Optional<Product> product = productService.getProductBySku(sku);

        assertThat(product.isPresent()).isFalse();
    }

    @Test
    @DisplayName("Should delete a product")
    public void deleteProductTest() {
        Product product = Product.builder().sku(1L).build();
        assertDoesNotThrow(() -> productService.delete(product));
        Mockito.verify(productRepository, Mockito.times(1)).delete(product);
    }

    @Test
    @DisplayName("Should throw error to try delete a product nonexistent")
    public void deleteInvalidProductTest() {
        Product product = new Product();
        assertThrows(IllegalArgumentException.class, () -> productService.delete(product));
        Mockito.verify(productRepository, Mockito.never()).delete(product);
    }

    @Test
    @DisplayName("Should update a product")
    public void updateProductTest() {
        Long sku = 1L;

        // Product to be update
        Product updatingProduct = createNewProduct();
        updatingProduct.setSku(sku);

        // Product simulation
        Product productUpdated = createNewProduct();
        productUpdated.setSku(sku);

        Mockito.when(productRepository.save(updatingProduct)).thenReturn(productUpdated);
        Product product = productService.update(updatingProduct);

        assertThat(product.getSku()).isEqualTo(productUpdated.getSku());
        assertThat(product.getName()).isEqualTo(productUpdated.getName());
        assertThat(product.getInventory()).isNotNull();
        assertThat(product.getInventory().getWarehouses()).isNotNull();

        List<Warehouse> warehousesFounded = product.getInventory().getWarehouses();
        List<Warehouse> warehousesMocked = productUpdated.getInventory().getWarehouses();

        for (int i = 0; i < warehousesFounded.size(); i++) {
            assertThat(warehousesFounded.get(i).getQuantity())
                .isEqualTo(warehousesMocked.get(i).getQuantity());
            assertThat(warehousesFounded.get(i).getLocality())
                .isEqualTo(warehousesMocked.get(i).getLocality());
            assertThat(warehousesFounded.get(i).getType())
                .isEqualTo(warehousesMocked.get(i).getType());
        }
    }

    @Test
    @DisplayName("Should throw error to try update a product nonexistent")
    public void updateInvalidProductTest() {
        Product product = new Product();
        assertThrows(IllegalArgumentException.class, () -> productService.update(product));
        Mockito.verify(productRepository, Mockito.never()).save(product);
    }

    private Product createNewProduct() {
        Warehouse warehouse1 = Warehouse.builder().locality("SP").quantity(12)
            .type(ProductTypeEnum.ECOMMERCE).build();

        Warehouse warehouse2 = Warehouse.builder().locality("MOEMA").quantity(3)
            .type(ProductTypeEnum.PHYSICAL_STORE).build();

        Inventory inventory = Inventory.builder()
            .warehouses(Arrays.asList(warehouse1, warehouse2))
            .quantity(15).build();

        return Product.builder().sku(1L).name("L'Oréal Professionnel")
            .inventory(inventory).build();
    }
}
