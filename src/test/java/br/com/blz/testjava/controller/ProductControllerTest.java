package br.com.blz.testjava.controller;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.mock.ProductMock;
import br.com.blz.testjava.service.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    private static final String productJson = "{\n" +
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

    @Before
    public void setUp() {
        final Product product = ProductMock.create("product1", 123321, 32, "SP", Warehouse.Type.PHYSICAL_STORE);
        productService.save(product);
    }

    @After
    public void tearDown() {
        try {
            productService.delete(123321L);
        } catch (Exception e) {}
    }

    @Test
    public void createProductTest() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/product")
                .content(productJson)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createDuplicatedProductTest() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/product")
                .content(productJson.replace("43264","123321"))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isConflict())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getOkTest() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/product/123321")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getNotFoundTest() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/product/123")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateTest() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.request(HttpMethod.PUT, "/product/123321")
                .content(productJson.replace("12", "2").replace("43264", "123321"))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void removeTest() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.request(HttpMethod.DELETE, "/product/123321")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

}
