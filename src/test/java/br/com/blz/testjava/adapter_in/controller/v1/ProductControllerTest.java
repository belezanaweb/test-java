package br.com.blz.testjava.adapter_in.controller.v1;

import br.com.blz.testjava.adapter_in.config.JacksonUtils;
import br.com.blz.testjava.adapter_in.request.InventoryRequest;
import br.com.blz.testjava.adapter_in.request.ProductRequest;
import br.com.blz.testjava.adapter_in.request.WareHousesRequest;
import br.com.blz.testjava.adapter_in.util.ConstantsNames;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    @DisplayName("Testing of creating a product")
    void createProductTest() throws Exception {

        mvc.perform(post(ConstantsNames.URI_VERSION_NAME)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(JacksonUtils.converteObjetoParaString(createProductResquest())))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(MockMvcResultMatchers.jsonPath("$.sku", Matchers.is(43264L), Long.class ))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.quantity", Matchers.is(12), Integer.class))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[0].locality", Matchers.is("SP") ))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[0].quantity", Matchers.is(12), Integer.class))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[0].type", Matchers.is("ECOMMERCE")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.isMarketable", Matchers.is(Boolean.TRUE)));

    }

    @Test
    @Order(2)
    @DisplayName("Testing of creating a product and duplicarte registre")
    void createDupliacateProductTest() throws Exception {

        mvc.perform(post(ConstantsNames.URI_VERSION_NAME)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(JacksonUtils.converteObjetoParaString(createProductResquest())))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.errosMensagem[0]", Matchers.is("Duplicate registre not permission")));


    }

    @Test
    @Order(3)
    @DisplayName("Testing to update a product")
    void updateProductTest() throws Exception {

        mvc.perform(put(ConstantsNames.URI_VERSION_NAME)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(JacksonUtils.converteObjetoParaString(updateProductResquest())))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.sku", Matchers.is(43264L), Long.class ))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.quantity", Matchers.is(15)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[0].locality", Matchers.is("SP") ))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[0].quantity", Matchers.is(12), Integer.class))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[0].type", Matchers.is("ECOMMERCE")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[1].locality", Matchers.is("MOEMA") ))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[1].quantity", Matchers.is(3), Integer.class))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[1].type", Matchers.is("PHYSICAL_STORE")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.isMarketable", Matchers.is(Boolean.TRUE)));
    }

    @Test
    @Order(4)
    @DisplayName("Product Search Test")
    void getProductTest() throws Exception {

        mvc.perform(get(ConstantsNames.URI_VERSION_NAME+ConstantsNames.URI_SKU, 43264L)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.sku", Matchers.is(43264L), Long.class ))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.quantity", Matchers.is(15)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[0].locality", Matchers.is("SP") ))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[0].quantity", Matchers.is(12), Integer.class))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[0].type", Matchers.is("ECOMMERCE")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[1].locality", Matchers.is("MOEMA") ))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[1].quantity", Matchers.is(3), Integer.class))
            .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[1].type", Matchers.is("PHYSICAL_STORE")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.isMarketable", Matchers.is(Boolean.TRUE)));
    }

    @Test
    @Order(5)
    @DisplayName("Test to delete a product")
    void deleteProductTest() throws Exception {

        mvc.perform(delete(ConstantsNames.URI_VERSION_NAME+ConstantsNames.URI_SKU, 43264L)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isOk());
    }



    private ProductRequest createProductResquest() {

        List<WareHousesRequest> wareHousesRequestList = new ArrayList<>();
        wareHousesRequestList.add(
            WareHousesRequest.builder()
                .locality("SP")
                .quantity(12)
                .type("ECOMMERCE")
                .build()
        );

        return ProductRequest.builder()
            .sku(43264L)
            .name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")
            .inventoryRequest(
                InventoryRequest.builder()
                    .wareHousesRequestList(wareHousesRequestList)
                    .build())
            .build();
    }

    private ProductRequest updateProductResquest() {

        ProductRequest productRequest = createProductResquest();

        productRequest
            .getInventoryRequest()
            .getWareHousesRequestList()
            .add(
                WareHousesRequest.builder()
                    .locality("MOEMA")
                    .quantity(3)
                    .type("PHYSICAL_STORE")
                    .build()
            );

        return productRequest;
    }


}
