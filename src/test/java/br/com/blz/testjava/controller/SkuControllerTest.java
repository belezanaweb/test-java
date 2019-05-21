package br.com.blz.testjava.controller;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.dto.InventoryDto;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SkuControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private SkuController skuControllerMock;

    @Mock
    private ProductService productServiceMock;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(skuControllerMock)
                .build();
    }

    @Test
    public void createOrUpdateProductTest() throws Exception {
        mockMvc.perform(
                post("/sku")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getContent()))
                .andExpect(content().string("Produto criado ou autalizado com sucesso"))
                .andExpect(status().isOk());
    }

    @Test
    public void createOrUpdateProductErrorTest() throws Exception {
        doThrow(Exception.class).when(productServiceMock).createOrUpdate(any());
        mockMvc.perform(
                post("/sku")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getContent()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void createProductTest() throws Exception {
        mockMvc.perform(
                post("/sku")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getContent()))
                .andExpect(content().string("Produto criado ou autalizado com sucesso"))
                .andExpect(status().isOk());
    }

    @Test
    public void createProductErrorTest() throws Exception {
        doThrow(Exception.class).when(productServiceMock).createOrUpdate(any());
        mockMvc.perform(
                post("/sku")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getContent()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void deleteProductTest() throws Exception {
        mockMvc.perform(
                delete("/sku/{sku}", 123456L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Sku 123456"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteProductErrorTest() throws Exception {
        doThrow(Exception.class).when(productServiceMock).delete(any());
        mockMvc.perform(
                delete("/sku/{sku}", 123456L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void retriverProductTest() throws Exception {
        Product product = new Product();
        product.setSku(43264L);
        product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");

        List<Warehouse> warehouses = new ArrayList();
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("SP");
        warehouse.setQuantity(12);
        warehouse.setType("ECOMMERCE");
        warehouses.add(warehouse);

        warehouse = new Warehouse();
        warehouse.setLocality("MOEMA");
        warehouse.setQuantity(3);
        warehouse.setType("PHYSICAL_STORE");
        warehouses.add(warehouse);

        InventoryDto inventoryDto = new InventoryDto(warehouses);

        ProductDto productDto = new ProductDto(product, inventoryDto);

        when(productServiceMock.retriver(any())).thenReturn(productDto);

        mockMvc.perform(
                get("/sku/{sku}", 123456L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(getContent()))
                .andExpect(status().isOk());
    }

    @Test
    public void retriverProductErrorTest() throws Exception {
        doThrow(Exception.class).when(productServiceMock).retriver(any());
        mockMvc.perform(
                get("/sku/{sku}", 43264)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    private String getContent() {
        return "{\n" +
                "    \"sku\": 43264,\n" +
                "    \"name\": \"L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g\",\n" +
                "    \"inventory\": {\n" +
                "        \"quantity\": 15,\n" +
                "        \"warehouses\": [\n" +
                "            {\n" +
                "                \"locality\": \"SP\",\n" +
                "                \"quantity\": 12,\n" +
                "                \"type\": \"ECOMMERCE\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"locality\": \"MOEMA\",\n" +
                "                \"quantity\": 3,\n" +
                "                \"type\": \"PHYSICAL_STORE\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"isMarketable\": true\n" +
                "}";
    }

}
