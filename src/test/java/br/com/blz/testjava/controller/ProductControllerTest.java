package br.com.blz.testjava.controller;

import br.com.blz.testjava.exception.ResourceAlreadyExistException;
import br.com.blz.testjava.exception.ResourceNotFoundException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.model.WarehouseType;
import br.com.blz.testjava.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void shouldAddElementAndReturnCreated() throws Exception {
        // given
        final Product mockProduct = getMockProduct();
        final Product savedProduct = getMockProduct();
        savedProduct.setMarketable(false);
        savedProduct.getInventory().setQuantity(0);

        // when
        when(productService.add(eq(mockProduct))).thenReturn(savedProduct);

        final ResultActions resultActions = mockMvc.perform(post("/api/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(mockProduct)));

        // then
        resultActions
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.sku", is(12)))
            .andExpect(jsonPath("$.name", is("test1")))
            .andExpect(jsonPath("$.marketable", is(false)))
            .andExpect(jsonPath("$.inventory.quantity", is(0)))
            .andExpect(jsonPath("$.inventory.warehouses[0].locality", is("MOEMA")))
            .andExpect(jsonPath("$.inventory.warehouses[0].quantity", is(20)))
            .andExpect(jsonPath("$.inventory.warehouses[0].type", is(WarehouseType.ECOMMERCE.name())));
        verify(productService, only()).add(any());
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void shouldTryToAddElementAndReturnConflict() throws Exception {
        // given
        final Product mockProduct = getMockProduct();

        // when
        when(productService.add(any())).thenThrow(new ResourceAlreadyExistException(String.format("Product with id %s already exists.", mockProduct.getSku())));

        final ResultActions resultActions = mockMvc.perform(post("/api/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(mockProduct)));

        // then
        resultActions.andExpect(status().isConflict());
        verify(productService, only()).add(any());
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void shouldGetElementAndReturnSuccess() throws Exception {
        // given
        final Product mockProduct = getMockProduct();
        mockProduct.getInventory().setQuantity(20);

        // when
        when(productService.get(eq(mockProduct.getSku()))).thenReturn(mockProduct);

        final ResultActions resultActions = mockMvc.perform(get("/api/get/12")
            .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.sku", is(12)))
            .andExpect(jsonPath("$.name", is("test1")))
            .andExpect(jsonPath("$.marketable", is(true)))
            .andExpect(jsonPath("$.inventory.quantity", is(20)))
            .andExpect(jsonPath("$.inventory.warehouses[0].locality", is("MOEMA")))
            .andExpect(jsonPath("$.inventory.warehouses[0].quantity", is(20)))
            .andExpect(jsonPath("$.inventory.warehouses[0].type", is(WarehouseType.ECOMMERCE.name())));
        verify(productService, only()).get(eq(mockProduct.getSku()));
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void shouldTryToGetElementAndReturnNotFound() throws Exception {
        // given
        final Product mockProduct = getMockProduct();

        // when
        when(productService.get(mockProduct.getSku())).thenThrow(new ResourceNotFoundException(String.format("Product with id %s does not exists.", mockProduct.getSku())));

        final ResultActions resultActions = mockMvc.perform(get("/api/get/12")
            .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isNotFound());
        verify(productService, only()).get(eq(mockProduct.getSku()));
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void shouldUpdateElementAndReturnSuccess() throws Exception {
        // given
        final Product mockProduct = getMockProduct();
        mockProduct.getInventory().setQuantity(20);

        // when
        when(productService.update(any())).thenReturn(mockProduct);

        final ResultActions resultActions = mockMvc.perform(patch("/api/update")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(mockProduct)));

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.sku", is(12)))
            .andExpect(jsonPath("$.name", is("test1")))
            .andExpect(jsonPath("$.marketable", is(true)))
            .andExpect(jsonPath("$.inventory.quantity", is(20)))
            .andExpect(jsonPath("$.inventory.warehouses[0].locality", is("MOEMA")))
            .andExpect(jsonPath("$.inventory.warehouses[0].quantity", is(20)))
            .andExpect(jsonPath("$.inventory.warehouses[0].type", is(WarehouseType.ECOMMERCE.name())));
        verify(productService, only()).update(eq(mockProduct));
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void shouldTryToUpdateElementAndReturnNotFound() throws Exception {
        // given
        final Product mockProduct = getMockProduct();

        // when
        when(productService.update(any()))
            .thenThrow(new ResourceNotFoundException(String.format("Product with id %s does not exists.", mockProduct.getSku())));

        final ResultActions resultActions = mockMvc.perform(patch("/api/update")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(mockProduct)));

        // then
        resultActions.andExpect(status().isNotFound());
        verify(productService, only()).update(eq(mockProduct));
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void shouldDeleteElementAndReturnNoContent() throws Exception {
        // given
        final Product mockProduct = getMockProduct();
        mockProduct.getInventory().setQuantity(20);

        // when
        doNothing().when(productService).delete(eq(mockProduct.getSku()));

        final ResultActions resultActions = mockMvc.perform(delete("/api/delete/12")
            .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isNoContent());
        verify(productService, only()).delete(eq(mockProduct.getSku()));
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void shouldTryToDeleteElementAndReturnNotFound() throws Exception {
        // given
        final Product mockProduct = getMockProduct();

        // when
        doThrow(new ResourceNotFoundException(String.format("Product with id %s does not exists.", mockProduct.getSku())))
            .when(productService)
            .delete(eq(mockProduct.getSku()));

        final ResultActions resultActions = mockMvc.perform(delete("/api/delete/12")
            .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isNotFound());
        verify(productService, only()).delete(eq(mockProduct.getSku()));
        verifyNoMoreInteractions(productService);
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

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
