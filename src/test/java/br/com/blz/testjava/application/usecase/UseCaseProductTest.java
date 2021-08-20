package br.com.blz.testjava.application.usecase;

import br.com.blz.testjava.application.domain.Inventory;
import br.com.blz.testjava.application.domain.Product;
import br.com.blz.testjava.application.domain.WareHouses;
import br.com.blz.testjava.application.port.out.IProductRepository;
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
class UseCaseProductTest {

    @InjectMocks
    UseCaseProduct useCaseProduct;

    @Mock
    private IProductRepository productRepository;

    @Test
    @DisplayName("Testing of creating a use case")
    void createProductTest(){
        Mockito.when(productRepository.saveProduct(Mockito.any())).thenReturn(createProduct());
        final Product productResult = useCaseProduct.createProduct(createProduct());
        Assertions.assertEquals(43264L, productResult.getSku());
    }

    @Test
    @DisplayName("Testing to update a product")
    void updateProductTest(){
        Mockito.when(productRepository.updateProduct(Mockito.any())).thenReturn(updateProduct());
        final Product productResult  = useCaseProduct.updateProduct(updateProduct());
        Assertions.assertEquals(43264L, productResult.getSku());

    }

    @Test
    @DisplayName("Product Search Test")
    void getProductTest(){
        Mockito.when(productRepository.findProduct(Mockito.any())).thenReturn(createProduct());

        Long sku = 43264L;
        final Product productResult =  useCaseProduct.findProduct(sku);
        Assertions.assertEquals(43264L, productResult.getSku());

    }

    @Test
    @DisplayName("Test to delete a product")
    void deleteProductTest(){

        Long sku = 43264L;
        useCaseProduct.deleteProduct(sku);
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


}
