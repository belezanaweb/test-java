package br.com.blz.testjava.controller;

import br.com.blz.testjava.controller.resources.ProductRequest;
import br.com.blz.testjava.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.blz.testjava.ProductMother.ProductResourcesMother.createProductRequest;
import static br.com.blz.testjava.ProductMother.ProductResourcesMother.createProductResponse;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest
@ContextConfiguration(classes = ProductController.class)
@RunWith(SpringRunner.class)
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private ProductController fixture;

    protected MockMvc mvc;

    @Before
    public void setUp() {
        initMocks(this);
        mvc = standaloneSetup(fixture).build();
    }

    @Test
    public void testGetProduct() throws Exception {
        String url = "/products/123";

        given(productService.getProductBySku(eq(123L)))
            .willReturn(createProductResponse());

        mvc.perform(get(url))
            .andExpect(status().isOk());

        then(productService).should().getProductBySku(eq(123L));
    }

    @Test
    public void testPostProduct() throws Exception {
        String url = "/products";

        ProductRequest request = createProductRequest();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);

        mvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated());

        then(productService).should().createProduct(eq(request));
    }

    @Test
    public void testPutProduct() throws Exception {
        String url = "/products/123";

        ProductRequest request = createProductRequest();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);

        given(productService.updateProduct(eq(request), eq(123L)))
            .willReturn(createProductResponse());

        mvc.perform(put(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk());

        then(productService).should().updateProduct(eq(request), eq(123L));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        String url = "/products/123";

        mvc.perform(delete(url))
            .andExpect(status().isOk());

        then(productService).should().deleteProduct(eq(123L));
    }
}

