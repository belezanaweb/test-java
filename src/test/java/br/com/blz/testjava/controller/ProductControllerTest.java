package br.com.blz.testjava.controller;

import br.com.blz.testjava.domain.request.ProductCreateRequest;
import br.com.blz.testjava.domain.request.ProductUpdateRequest;
import br.com.blz.testjava.domain.response.ProductResponse;
import br.com.blz.testjava.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

import static br.com.blz.testjava.mother.ProductMother.getProductCreateRequest;
import static br.com.blz.testjava.mother.ProductMother.getProductResponse;
import static br.com.blz.testjava.mother.ProductMother.getProductUpdateRequest;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ProductControllerTest {

    public static final String PRODUCT_SKU = "/product/{sku}";

    @Autowired
    private ObjectMapper mapper;

    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductService productService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testCreateProductSuccess() throws Exception {
        ProductResponse productResponse = getProductResponse();
        when(productService.createProduct(any(ProductCreateRequest.class)))
            .thenReturn(productResponse);

        String jsonString = mapper.writeValueAsString(getProductCreateRequest());

        mockMvc.perform(post("/product")
            .content(jsonString)
            .locale(Locale.ENGLISH)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.name").value(productResponse.getName()))
            .andExpect(jsonPath("$.sku").value(productResponse.getSku()));
    }

    @Test
    public void testUpdateProductSuccess() throws Exception {
        ProductResponse productResponse = getProductResponse();
        when(productService.updateProduct(anyLong(), any(ProductUpdateRequest.class)))
            .thenReturn(productResponse);

        String jsonString = mapper.writeValueAsString(getProductUpdateRequest());

        mockMvc.perform(put(PRODUCT_SKU.replace("{sku}", "1"))
            .content(jsonString)
            .locale(Locale.ENGLISH)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.name").value(productResponse.getName()))
            .andExpect(jsonPath("$.sku").value(productResponse.getSku()));
    }

    @Test
    public void testGetProductSuccess() throws Exception {
        ProductResponse productResponse = getProductResponse();
        when(productService.getProduct(anyLong())).thenReturn(productResponse);

        mockMvc.perform(get(PRODUCT_SKU.replace("{sku}", "1")))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.name").value(productResponse.getName()))
            .andExpect(jsonPath("$.sku").value(productResponse.getSku()));

        verify(productService).getProduct(anyLong());
    }

    @Test
    public void testDeleteProductSuccess() throws Exception {
        doNothing().when(productService).deleteProduct(anyLong());

        mockMvc.perform(delete(PRODUCT_SKU.replace("{sku}", "1")))
            .andExpect(status().isOk());

        verify(productService).deleteProduct(anyLong());
    }
}
