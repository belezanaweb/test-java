package br.com.blz.testjava;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.blz.testjava.model.entity.Inventory;
import br.com.blz.testjava.model.entity.Product;
import br.com.blz.testjava.model.entity.Warehouse;
import br.com.blz.testjava.model.entity.Warehouse.Type;
import br.com.blz.testjava.repository.ProductRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@SpringBootTest
public class TestJavaApplicationTests {


    private static final Long DEFAULT_SKU = 1234L;
    private static final String DEFAULT_NAME = "Produto 1";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    private Product product;

    private static final ObjectMapper mapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return mapper;
    }

    @BeforeEach
    public void initTest() {
        Warehouse warehouse1 = Warehouse.builder().locality("Sao Paulo").type(Type.ECOMMERCE)
            .quantity(34).build();
        Warehouse warehouse2 = Warehouse.builder().locality("Londrina").type(Type.PHYSICAL_STORE)
            .quantity(13).build();
        Inventory inventory = Inventory.builder().warehouse(warehouse1).warehouse(warehouse2)
            .build();
        product = Product.builder().inventory(inventory).sku(DEFAULT_SKU).name(DEFAULT_NAME)
            .build();
        productRepository.clear();
    }

    @org.junit.jupiter.api.Test
    @Transactional
    public void createProduct() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        mockMvc.perform(post("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsBytes(product)))
            .andExpect(status().isCreated());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate + 1);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct).isNotNull();
        assertThat(testProduct.getSku()).isEqualTo(DEFAULT_SKU);
        assertThat(testProduct.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProduct.getIsMarketable()).isEqualTo(true);
        assertThat(testProduct.getInventory()).isNotNull();
        assertThat(testProduct.getInventory().getWarehouses()).isNotNull();
        assertThat(testProduct.getInventory().getWarehouses().size()).isEqualTo(2);
    }

    @org.junit.jupiter.api.Test
    @Transactional
    public void createProductWithExistingSku() throws Exception {
        productRepository.save(product);
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        mockMvc.perform(post("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsBytes(product)))
            .andExpect(status().isBadRequest());
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate);
    }


    @org.junit.jupiter.api.Test
    @Transactional
    public void getProductBySky() throws Exception {
        productRepository.save(product);

        mockMvc.perform(get("/api/products/{sku}", this.product.getSku()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.sku").value(DEFAULT_SKU.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.inventory.quantity").value(47));
    }

    @org.junit.jupiter.api.Test
    public void getNonExistingProduct() throws Exception {
        mockMvc.perform(get("/api/products/{sku}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @org.junit.jupiter.api.Test
    @Transactional
    public void updateProduct() throws Exception {
        productRepository.save(product);
        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        Product updatedProduct = productRepository.find(product.getSku());
        updatedProduct.setName("Changed Product");
        updatedProduct.setInventory(
            Inventory.builder()
                .warehouse(Warehouse.builder()
                    .locality("Porto Alegre")
                    .quantity(12)
                    .type(Type.PHYSICAL_STORE)
                    .build())
                .build());

        mockMvc.perform(put("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsBytes(updatedProduct)))
            .andExpect(status().isOk());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct).isNotNull();
        assertThat(testProduct.getName()).isEqualTo("Changed Product");
        assertThat(testProduct.getInventory()).isNotNull();
        assertThat(testProduct.getInventory().getQuantity()).isEqualTo(12);
        assertThat(testProduct.getInventory().getWarehouses()).isNotNull();
        assertThat(testProduct.getInventory().getWarehouses().size()).isEqualTo(1);
    }

    @org.junit.jupiter.api.Test
    @Transactional
    public void updateNonExistingProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        mockMvc.perform(put("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProduct() throws Exception {
        productRepository.save(product);
        int databaseSizeBeforeDelete = productRepository.findAll().size();

        mockMvc.perform(delete("/api/products/{id}", product.getSku())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeDelete - 1);
    }

}
