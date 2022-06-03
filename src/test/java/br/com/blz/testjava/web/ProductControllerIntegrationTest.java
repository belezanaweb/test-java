package br.com.blz.testjava.web;

import br.com.blz.testjava.config.RedisEmbeddedConfig;
import br.com.blz.testjava.core.domain.WarehouseType;
import br.com.blz.testjava.web.request.CreateProductRequestDTO;
import br.com.blz.testjava.web.request.InventoryRequestDTO;
import br.com.blz.testjava.web.request.WarehouseDTO;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@Import({RedisEmbeddedConfig.class})
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    static final String BASE_URL = "/products";

    private static WireMockServer wireMockServer;

    @Autowired
    private MockMvc mockMvc;

    private CreateProductRequestDTO createProduct;

    private String sku;

    @BeforeAll
    static void init() {
        wireMockServer = new WireMockServer(options().port(3001));
        wireMockServer.start();
    }

    @BeforeEach
    void setup() throws Exception {
        sku = "4567";
        createProduct = CreateProductRequestDTO.builder()
            .sku(Integer.valueOf(sku))
            .name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")
            .inventory(InventoryRequestDTO.builder()
                .warehouses(List.of(
                    WarehouseDTO.builder()
                        .locality("SP")
                        .quantity(12)
                        .type(WarehouseType.ECOMMERCE)
                        .build(),
                    WarehouseDTO.builder()
                        .locality("MOEMA")
                        .quantity(3)
                        .type(WarehouseType.PHYSICAL_STORE)
                        .build()
                )).build()).build();

        mockMvc.perform(delete(BASE_URL + "/"+ sku)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @AfterAll
    static void destroy() {
        wireMockServer.stop();
    }

    @Test
    public void when_createProductWithSuccess_expect_createdStatus() throws Exception {
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(createProduct)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void when_createRepeatedProduct_expect_BadRequest() throws Exception {
        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(createProduct)))
            .andDo(print())
            .andExpect(status().isCreated());

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(createProduct)))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code", equalTo(400)))
            .andExpect(jsonPath("$.error", equalTo("Bad Request")))
            .andExpect(jsonPath("$.message", equalTo("There is already a product registered with this sku.")))
            .andExpect(jsonPath("$.stackTrace", equalTo("There is already a product registered with this sku.")));
    }

    @Test
    void when_createWithNullField_expect_BadRequest() throws Exception {
        createProduct.setSku(null);
        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(createProduct)))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code", equalTo(400)))
            .andExpect(jsonPath("$.error", equalTo("Bad Request")))
            .andExpect(jsonPath("$.message", equalTo("Invalid Arguments")))
            .andExpect(jsonPath("$.fieldErrors[0].field", equalTo("sku")))
            .andExpect(jsonPath("$.fieldErrors[0].message", equalTo("Field sku could not be null.")));
    }

    @Test
    void when_deleteProductWithSuccess_expect_okStatus() throws Exception {
        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(createProduct)))
            .andDo(print())
            .andExpect(status().isCreated());

        mockMvc.perform(delete(BASE_URL + "/"+ sku)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void when_findExistentProduct_expect_statusOk() throws Exception {
        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(createProduct)))
            .andDo(print())
            .andExpect(status().isCreated());

        mockMvc.perform(get(BASE_URL + "/" + sku)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.sku", equalTo(Integer.valueOf(sku))))
            .andExpect(jsonPath("$.name", equalTo("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")))
            .andExpect(jsonPath("$.inventory.quantity", equalTo(2)))
            .andExpect(jsonPath("$.isMarketable", equalTo(true)));
    }

    @Test
    void when_findNonExistentProduct_expect_NotFound() throws Exception {
        mockMvc.perform(get(BASE_URL + "/" + 1234)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code", equalTo(400)))
            .andExpect(jsonPath("$.message", equalTo("Non-existent product")))
            .andExpect(jsonPath("$.stackTrace", equalTo("Non-existent product")));
    }

    @Test
    void when_updateProductWithSuccess_expect_okStatus() throws Exception {
        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(createProduct)))
            .andDo(print())
            .andExpect(status().isCreated());

        createProduct.setName("Lily");
        mockMvc.perform(put(BASE_URL + "/"+ sku)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(createProduct)))
            .andDo(print())
            .andExpect(status().isOk());

        mockMvc.perform(get(BASE_URL + "/" + sku)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", equalTo("Lily")));
    }

}
