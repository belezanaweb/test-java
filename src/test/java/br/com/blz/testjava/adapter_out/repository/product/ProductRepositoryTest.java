package br.com.blz.testjava.adapter_out.repository.product;

import br.com.blz.testjava.adapter_in.exception.custom_exception.DuplicateRecordException;
import br.com.blz.testjava.adapter_in.exception.custom_exception.ResultNotFoundException;
import br.com.blz.testjava.adapter_out.entity.InventoryEntity;
import br.com.blz.testjava.adapter_out.entity.ProductEntity;
import br.com.blz.testjava.adapter_out.entity.WareHousesEntity;
import br.com.blz.testjava.adapter_out.mapper.IProductOutMapper;
import br.com.blz.testjava.application.domain.Inventory;
import br.com.blz.testjava.application.domain.Product;
import br.com.blz.testjava.application.domain.WareHouses;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    private ProductRepository productRepository;

    @Mock
    private IProductOutMapper productMapper;


    @Test
    @DisplayName("test save product in mockdb")
    void saveProductTest(){
        ProductEntity productEntity = createProductEntity();
        Product product = createProduct();

        Mockito.when(productMapper.toEntity(product)).thenReturn(productEntity);
        Mockito.when(productMapper.toDomain(productEntity)).thenReturn(product);

        Product productSalve = productRepository.saveProduct(product);

        Assertions.assertEquals(product.getSku(), productSalve.getSku());
    }

    @Test
    @DisplayName("test to save duplicate product in mockdb")
    void saveProductDuplicateExceptionTest(){
        ProductEntity productEntity = createProductEntity();
        Product product = createProduct();

        Mockito.when(productMapper.toEntity(product)).thenReturn(productEntity);
        Mockito.when(productMapper.toDomain(productEntity)).thenReturn(product);

        Product productSalve = productRepository.saveProduct(product);

        Assertions.assertEquals(product.getSku(), productSalve.getSku());

        Assertions.assertThrows(DuplicateRecordException.class, () -> productRepository.saveProduct(product));
    }

    @Test
    @DisplayName("product update test in mockdb")
    void updateProductTest(){

        ProductEntity productEntity = createProductEntity();
        Product product = createProduct();

        Mockito.when(productMapper.toEntity(product)).thenReturn(productEntity);
        Mockito.when(productMapper.toDomain(productEntity)).thenReturn(product);

        Product productSalve = productRepository.saveProduct(product);
        Assertions.assertEquals(product.getSku(), productSalve.getSku());


        ProductEntity updateProductEntity = updateProductEntity();
        Product updateProduct = updateProduct();

        Mockito.when(productMapper.toEntity(updateProduct)).thenReturn(updateProductEntity);
        Mockito.when(productMapper.toDomain(updateProductEntity)).thenReturn(updateProduct);


        Product productSalveUpdate = productRepository.updateProduct(updateProduct);
        Assertions.assertEquals(product.getSku(), productSalveUpdate.getSku());
        Assertions.assertEquals(2, productSalveUpdate.getInventory().getWareHousesList().size());

    }

    @Test
    @DisplayName("sku search test in mockdb")
    void findProductTest(){

        ProductEntity productEntity = createProductEntity();
        Product product = createProduct();

        Mockito.when(productMapper.toEntity(product)).thenReturn(productEntity);
        Mockito.when(productMapper.toDomain(productEntity)).thenReturn(product);

        Product productSalve = productRepository.saveProduct(product);
        Assertions.assertEquals(product.getSku(), productSalve.getSku());


        Long sku = 43264L;
        Product produtoFind = productRepository.findProduct(sku);
        Assertions.assertEquals(sku, produtoFind.getSku());

    }

    @Test
    @DisplayName("sku search test for a product, not found in mockdb")
    void findProductNotFoundExceptionTest(){
        Long sku = 43264L;
        Assertions.assertThrows(ResultNotFoundException.class,
                               ()-> productRepository.findProduct(sku));
    }

    @Test
    @DisplayName("test to delete a product by sku in mockdb")
    void deleteProductTest(){

        ProductEntity productEntity = createProductEntity();
        Product product = createProduct();

        Mockito.when(productMapper.toEntity(product)).thenReturn(productEntity);
        Mockito.when(productMapper.toDomain(productEntity)).thenReturn(product);

        Product productSalve = productRepository.saveProduct(product);
        Assertions.assertEquals(product.getSku(), productSalve.getSku());

        Long sku = 43264L;
        productRepository.deleteProduct(sku);
    }

    @Test
    @DisplayName("teste de deletar  um produto pelo sku, não encontrado em mockdb")
    void deleteProductNotFoudExceptionTest(){
        Long sku = 43264L;
        Assertions.assertThrows(ResultNotFoundException.class,
            ()-> productRepository.deleteProduct(sku));
    }


    private Product createProduct() {

        List<WareHouses> wareHousesList = new ArrayList<>();
        wareHousesList.add(
            WareHouses.builder()
                .locality("SP")
                .quantity(12)
                .type("ECOMMERCE")
                .build()
        );

        return Product.builder()
            .sku(43264L)
            .name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")
            .inventory(
                Inventory.builder()
                    .wareHousesList(wareHousesList)
                    .build())
            .build();
    }

    private Product updateProduct() {

        Product product = createProduct();

        product
            .getInventory()
            .getWareHousesList()
            .add(
                WareHouses.builder()
                    .locality("MOEMA")
                    .quantity(3)
                    .type("PHYSICAL_STORE")
                    .build()
            );

        return product;
    }

    private ProductEntity createProductEntity() {

        List<WareHousesEntity> wareHousesEntityList = new ArrayList<>();
        wareHousesEntityList.add(
            WareHousesEntity.builder()
                .locality("SP")
                .quantity(12)
                .type("ECOMMERCE")
                .build()
        );

        return ProductEntity.builder()
            .sku(43264L)
            .name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")
            .inventoryEntity(
                InventoryEntity.builder()
                    .wareHousesEntityList(wareHousesEntityList)
                    .build())
            .build();
    }

    private ProductEntity updateProductEntity() {

        ProductEntity productEntity = createProductEntity();

        productEntity
            .getInventoryEntity()
            .getWareHousesEntityList()
            .add(
                WareHousesEntity.builder()
                    .locality("MOEMA")
                    .quantity(3)
                    .type("PHYSICAL_STORE")
                    .build()
            );

        return productEntity;
    }
}
