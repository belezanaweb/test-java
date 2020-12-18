package br.com.blz.testjava.services;

import br.com.blz.testjava.api.vos.InventoryVO;
import br.com.blz.testjava.api.vos.ProductVO;
import br.com.blz.testjava.api.vos.WarehouseVO;
import br.com.blz.testjava.model.entities.Locality;
import br.com.blz.testjava.model.entities.Product;
import br.com.blz.testjava.model.entities.Warehouse;
import br.com.blz.testjava.model.enums.TypeWarehouseEnum;
import br.com.blz.testjava.model.repositories.LocalityRepository;
import br.com.blz.testjava.model.repositories.ProductRepository;
import br.com.blz.testjava.model.repositories.WarehouseRepository;
import br.com.blz.testjava.services.impl.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ProductServiceTest {

    ProductService service;

    @MockBean
    ProductRepository repository;

    @MockBean
    LocalityRepository localityRepository;

    @MockBean
    WarehouseRepository warehouseRepository;

    @BeforeEach
    public void setup() {
        this.service = new ProductServiceImpl(repository, localityRepository, warehouseRepository);
    }

    @Test
    @DisplayName("Deve econtrar um produto por sku")
    public void findBySkuTest() {
        Long sku = 43264L;

        Product product = Product.builder()
            .sku(sku)
            .name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")
            .build();

        Warehouse w1 = Warehouse.builder()
            .id(1L)
            .locality(Locality.builder().id(1L).name("SP").build())
            .quantity(12L)
            .type(TypeWarehouseEnum.ECOMMERCE)
            .product(product)
            .build();

        Warehouse w2 = Warehouse.builder()
            .id(1L)
            .locality(Locality.builder().id(2L).name("MOEMA").build())
            .quantity(3L)
            .type(TypeWarehouseEnum.PHYSICAL_STORE)
            .product(product)
            .build();

        List<Warehouse> inventory = new ArrayList<>();
        inventory.add(w1);
        inventory.add(w2);

        product.setInventory(inventory);

        Mockito.when(repository.findBySku(sku)).thenReturn(Optional.of(product));

        Product productFinded = service.findBySku(sku);

        assertThat(productFinded.getSku()).isEqualTo(sku);
        assertThat(productFinded.getName()).isEqualTo(product.getName());
        assertThat(productFinded.getInventory()).isNotEmpty();
    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver um produto para o sku.")
    public void findBySkuInvalidTest() {
        Mockito.when(repository.findBySku(Mockito.anyLong())).thenReturn(Optional.empty());
        Throwable exception = Assertions.catchThrowable(() -> service.findBySku(1L));
        assertThat(exception).isInstanceOf(EmptyResultDataAccessException.class)
            .hasMessage("Produto não encontrado com sku 1");
    }

    @Test
    @DisplayName("Deve criar um novo Produto.")
    public void createProductTest() throws Exception {
        Long sku = 55986L;

        ProductVO vo = ProductVO.builder()
            .sku(sku)
            .name("Floratta Flores Secretas Desodorante Colônia 30ml")
            .build();

        WarehouseVO wv1 = WarehouseVO.builder()
            .locality("SP")
            .quantity(2L)
            .type(TypeWarehouseEnum.ECOMMERCE)
            .build();

        WarehouseVO wv2 = WarehouseVO.builder()
            .locality("MOEMA")
            .quantity(2L)
            .type(TypeWarehouseEnum.PHYSICAL_STORE)
            .build();

        List<WarehouseVO> wharehousesVO = new ArrayList<WarehouseVO>();
        wharehousesVO.add(wv1);
        wharehousesVO.add(wv2);

        InventoryVO inventoryVO = new InventoryVO();
        inventoryVO.setWarehouses(wharehousesVO);

        vo.setInventory(inventoryVO);

        Mockito.when(repository.save(Mockito.any(Product.class))).thenReturn(Product.builder().sku(sku).build());

        Mockito.when(localityRepository.findByName(Mockito.anyString()))
            .thenReturn(Optional.of(Locality.builder().name("SP").id(1L).build()));

        org.junit.jupiter.api.Assertions
            .assertDoesNotThrow(() -> service.save(vo));

        Mockito.verify(repository, Mockito.times(1))
            .save(Mockito.any(Product.class));
    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver um produto para o sku ao tentar excluir.")
    public void deleteProductSkuInvalidTest() {
        Mockito.when(repository.findBySku(Mockito.anyLong())).thenReturn(Optional.empty());

        Throwable exception = Assertions.catchThrowable(() -> service.delete(1L));

        assertThat(exception).isInstanceOf(EmptyResultDataAccessException.class)
            .hasMessage("Produto não encontrado com sku 1");
    }

    @Test
    @DisplayName("Deve deletar um produto.")
    public void deleteProductTest() {
        Long sku = 43264L;

        Mockito.when(repository.findBySku(Mockito.anyLong()))
            .thenReturn(Optional.of(Product.builder().sku(sku).build()));

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> service.delete(sku));

        Mockito.verify(repository, Mockito.times(1)).delete(Mockito.any(Product.class));
    }

    @Test
    @DisplayName("Deve atualizar um produto.")
    public void updateProductTest() {
        Long sku = 43264L;

        Product product = Product.builder()
            .sku(sku)
            .name("Floratta Flores Secretas Desodorante Colônia 30ml")
            .build();

        Warehouse w1 = Warehouse.builder()
            .id(1L)
            .locality(Locality.builder().id(1L).name("SP").build())
            .quantity(2L)
            .type(TypeWarehouseEnum.ECOMMERCE)
            .product(product)
            .build();

        Warehouse w2 = Warehouse.builder()
            .id(1L)
            .locality(Locality.builder().id(2L).name("MOEMA").build())
            .quantity(2L)
            .type(TypeWarehouseEnum.PHYSICAL_STORE)
            .product(product)
            .build();

        List<Warehouse> inventory = new ArrayList<>();
        inventory.add(w1);
        inventory.add(w2);

        product.setInventory(inventory);

        ProductVO vo = ProductVO.builder()
            .sku(sku)
            .name("Floratta Flores Secretas Desodorante Colônia 30ml")
            .build();

        WarehouseVO wv1 = WarehouseVO.builder()
            .locality("SP")
            .quantity(12L)
            .type(TypeWarehouseEnum.ECOMMERCE)
            .build();

        List<WarehouseVO> wharehousesVO = new ArrayList<>();
        wharehousesVO.add(wv1);

        InventoryVO inventoryVO = new InventoryVO();
        inventoryVO.setWarehouses(wharehousesVO);

        vo.setInventory(inventoryVO);

        Mockito.when(localityRepository.findByName("SP"))
            .thenReturn(Optional.of(Locality.builder().id(1L).name("SP").build()));

        Mockito.when(warehouseRepository.findByProductSkuAndNameLocality(sku, "SP"))
            .thenReturn(Optional.of(w1));

        Mockito.when(repository.findBySku(sku))
            .thenReturn(Optional.of(product));

        Mockito.when(repository.save(Mockito.any(Product.class)))
            .thenReturn(Product.builder().sku(sku).build());

        org.junit.jupiter.api.Assertions
            .assertDoesNotThrow(() -> service.update(vo, sku));

        Mockito.verify(repository, Mockito.times(1))
            .save(Mockito.any(Product.class));
    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver um produto para o sku ao tentar atualizar.")
    public void updateProductSkuInvalidTest() {
        Mockito.when(repository.findBySku(Mockito.anyLong())).thenReturn(Optional.empty());

        Throwable exception = Assertions.catchThrowable(() -> service.update(ProductVO.builder().sku(0L).build(), 0L));

        assertThat(exception).isInstanceOf(EmptyResultDataAccessException.class)
            .hasMessage("Produto não encontrado com sku 0");
    }
}
