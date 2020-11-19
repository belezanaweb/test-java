package br.com.blz.testjava.api.resources;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.blz.testjava.api.dtos.ProductDTO;
import br.com.blz.testjava.model.entities.Product;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class ProductResourceTest {

    static String PRODUCT_API = "/api/products";

    @Autowired
    MockMvc mvc;

    @MockBean
    ProductService productService;

    @Test
    @DisplayName("Should create a product with success.")
    public void createProductTest() throws Exception {
        ProductDTO productDTO = ProductDTO.builder().name("L'Oréal Professionnel").build();
        Product savedProduct = Product.builder().sku(43264L).name("L'Oréal Professionnel").build();

        BDDMockito.given(productService.save(Mockito.any(Product.class))).willReturn(savedProduct);
        String content = new ObjectMapper().writeValueAsString(productDTO);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(PRODUCT_API)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(content);

        mvc.perform(request)
            .andExpect(status().isCreated())
            .andExpect(jsonPath("sku").value("43264"))
            .andExpect(jsonPath("name").value(productDTO.getName()));
    }

    @Test
    @DisplayName("Should launch validation error when there is not enough data to create the product.")
    public void createInvalidProductTest() throws Exception {
        String content = new ObjectMapper().writeValueAsString(new ProductDTO());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(PRODUCT_API)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(content);

        mvc.perform(request)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("errors", hasSize(1)));
    }

    @Test
    @DisplayName("Should issue a conflict error if the product already exists.")
    public void createDuplicateProductTest() {
        // cenário
    }

}
