package br.com.blz.testjava.api.controllers;

import br.com.blz.testjava.api.vos.InventoryVO;
import br.com.blz.testjava.api.vos.ProductVO;
import br.com.blz.testjava.api.vos.WarehouseVO;
import br.com.blz.testjava.model.entities.Locality;
import br.com.blz.testjava.model.entities.Product;
import br.com.blz.testjava.model.entities.Warehouse;
import br.com.blz.testjava.model.enums.TypeWarehouseEnum;
import br.com.blz.testjava.model.exceptions.ProductUniqueKeyException;
import br.com.blz.testjava.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ProductService service;

    static String PRODUCT_API = "/api/products/";

    @Test
    @DisplayName("Deve recuperar um produto.")
    public void findBySkuTest() throws Exception {
        Long sku = 43264L;

        Product product = Product.builder()
            .sku(sku)
            .name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")
            .build();

        Warehouse w1 = Warehouse.builder()
            .locality(new Locality().builder().name("SP").build())
            .quantity(12L)
            .type(TypeWarehouseEnum.ECOMMERCE)
            .build();

        Warehouse w2 = Warehouse.builder()
            .locality(new Locality().builder().name("MOEMA").build())
            .quantity(3L)
            .type(TypeWarehouseEnum.ECOMMERCE)
            .build();

        List<Warehouse> inventory = new ArrayList<>();
        inventory.add(w1);
        inventory.add(w2);

        product.setInventory(inventory);

        BDDMockito.given(service.findBySku(Mockito.anyLong())).willReturn(product);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .get(PRODUCT_API + sku)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("sku").value(sku))
            .andExpect(jsonPath("name").value(product.getName()))
            .andExpect(jsonPath("isMarketable").value(true))
            .andExpect(jsonPath("inventory").isNotEmpty())
            .andExpect(jsonPath("inventory.quantity").value(15L));
    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver um produto para o sku.")
    public void findBySkuInvalidTest() throws Exception {
        BDDMockito.given(service.findBySku(Mockito.anyLong()))
            .willThrow(new EmptyResultDataAccessException("", 0));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .get(PRODUCT_API + 0)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("title").value("Entidade não encontrada."));
    }

    @Test
    @DisplayName("Deve criar um novo Produto.")
    public void createProductTest() throws Exception {
        ProductVO vo = ProductVO.builder()
            .sku(55986L)
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

        Product product = Product.builder()
            .sku(55986L)
            .name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")
            .build();

        Warehouse wd1 = Warehouse.builder()
            .locality(Locality.builder().name("SP").build())
            .quantity(12L)
            .type(TypeWarehouseEnum.ECOMMERCE)
            .build();

        Warehouse wd2 = Warehouse.builder()
            .locality(Locality.builder().name("MOEMA").build())
            .quantity(3L)
            .type(TypeWarehouseEnum.ECOMMERCE)
            .build();

        List<Warehouse> inventory = new ArrayList<>();
        inventory.add(wd1);
        inventory.add(wd2);

        product.setInventory(inventory);

        BDDMockito.given(service.save(Mockito.any(ProductVO.class))).willReturn(product);

        String json = new ObjectMapper().writeValueAsString(vo);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(PRODUCT_API)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json);

        mvc.perform(request)
            .andExpect(status().isCreated())
            .andExpect(jsonPath("sku").value(55986L))
            .andExpect(jsonPath("name").value(product.getName()))
            .andExpect(jsonPath("inventory").isNotEmpty());
    }

    @Test
    @DisplayName("Deve lançar expcetion para produto com sku uplicado.")
    public void createProductDuplicateSkuTest() throws Exception {
        ProductVO vo = ProductVO.builder()
            .sku(43264L)
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

        String json = new ObjectMapper().writeValueAsString(vo);

        BDDMockito.given(service.save(vo))
            .willThrow(new ProductUniqueKeyException(""));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(PRODUCT_API)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json);

        mvc.perform(request)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("title").value("Entidade já salva no banco de dados e não pode ser duplicada."));
    }

    @Test
    @DisplayName("Deve lançar exception para o produto nao preenchido correto.")
    public void createInvalidProductTest() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new ProductVO());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(PRODUCT_API)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json);

        mvc.perform(request)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("fields").isNotEmpty())
            .andExpect(jsonPath("fields[*].name", hasItem("sku")))
            .andExpect(jsonPath("fields[*].name", hasItem("name")))
            .andExpect(jsonPath("fields[*].name", hasItem("inventory")));
    }

    @Test
    @DisplayName("Deve lançar exception para o produto com invetory nao preenchido correto.")
    public void createInvalidProductInventoryTest() throws Exception {
        InventoryVO inventoryVO = new InventoryVO();

        ProductVO vo = ProductVO.builder()
            .sku(55986L)
            .name("Floratta Flores Secretas Desodorante Colônia 30ml")
            .inventory(inventoryVO)
            .build();

        String json = new ObjectMapper().writeValueAsString(vo);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(PRODUCT_API)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json);

        mvc.perform(request)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("fields").isNotEmpty())
            .andExpect(jsonPath("fields[*].name", hasItem("inventory.warehouses")));
    }

    @Test
    @DisplayName("Deve lançar exception para o produto com warehouse nao preenchido correto.")
    public void createInvalidProductInventoryWarehouseTest() throws Exception {
        InventoryVO inventoryVO = new InventoryVO();
        WarehouseVO warehouseVO = new WarehouseVO();
        inventoryVO.setWarehouses(Arrays.asList(warehouseVO));

        ProductVO vo = ProductVO.builder()
            .sku(55986L)
            .name("Floratta Flores Secretas Desodorante Colônia 30ml")
            .inventory(inventoryVO)
            .build();

        String json = new ObjectMapper().writeValueAsString(vo);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(PRODUCT_API)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json);

        mvc.perform(request)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("fields").isNotEmpty())
            .andExpect(jsonPath("fields[*].name", hasItem("inventory.warehouses[0].locality")))
            .andExpect(jsonPath("fields[*].name", hasItem("inventory.warehouses[0].quantity")))
            .andExpect(jsonPath("fields[*].name", hasItem("inventory.warehouses[0].type")));
    }

    @Test
    @DisplayName("Deve excluir um produto por sku.")
    public void deleteProductTest() throws Exception {
        Long sku = 43264L;

        BDDMockito.given(service.delete(Mockito.anyLong())).willReturn(true);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .delete(PRODUCT_API + sku)
            .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
            .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver um produto para o sku que deseja deletar.")
    public void deleteProductSkuInvalidTest() throws Exception {
        BDDMockito.given(service.delete(Mockito.anyLong()))
            .willThrow(new EmptyResultDataAccessException("", 0));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .delete(PRODUCT_API + 0)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("title").value("Entidade não encontrada."));
    }

    @Test
    @DisplayName("Deve atualizar o produto com os dados enviados com base no sku.")
    public void updateProductTest() throws Exception{
        Long sku = 43264L;

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

        Product product = Product.builder()
            .sku(55986L)
            .name("Floratta Flores Secretas Desodorante Colônia 30ml")
            .build();

        Warehouse wd1 = Warehouse.builder()
            .locality(Locality.builder().name("SP").build())
            .quantity(12L)
            .type(TypeWarehouseEnum.ECOMMERCE)
            .build();

        Warehouse wd2 = Warehouse.builder()
            .locality(Locality.builder().name("MOEMA").build())
            .quantity(3L)
            .type(TypeWarehouseEnum.ECOMMERCE)
            .build();

        List<Warehouse> inventory = new ArrayList<>();
        inventory.add(wd1);
        inventory.add(wd2);

        product.setInventory(inventory);

        BDDMockito.given(service.update(Mockito.any(ProductVO.class), Mockito.anyLong()))
            .willReturn(product);

        String json = new ObjectMapper().writeValueAsString(vo);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .put(PRODUCT_API + sku)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json);

        mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("sku").value(55986L))
            .andExpect(jsonPath("name").value(product.getName()))
            .andExpect(jsonPath("inventory").isNotEmpty());
    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver um produto para o sku que deseja atualizar.")
    public void updateProductSkuInvalidTest() throws Exception {
        Long sku = 0L;

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

        String json = new ObjectMapper().writeValueAsString(vo);

        BDDMockito.given(service.update(Mockito.any(ProductVO.class), Mockito.anyLong()))
            .willThrow(new EmptyResultDataAccessException("", 0));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .put(PRODUCT_API + sku)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json);

        mvc.perform(request)
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("title").value("Entidade não encontrada."));
    }
}
