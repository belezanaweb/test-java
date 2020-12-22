package br.com.blz.testjava.controller;

import br.com.blz.testjava.controller.resources.ProductRequest;
import br.com.blz.testjava.exception.NotFoundException;
import br.com.blz.testjava.exception.SkuAlreadyRegisteredException;
import br.com.blz.testjava.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.blz.testjava.mother.ProductMother.createProductRequest;
import static br.com.blz.testjava.mother.ProductMother.createProductResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest
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

        given(productService.getProductBySku(123L))
            .willReturn(createProductResponse());

        mvc.perform(get(url))
            .andExpect(status().isOk());

        then(productService).should().getProductBySku(123L);
    }

    @Test
    public void testGetProductNotFound() throws Exception {
        String url = "/products/123";

        given(productService.getProductBySku(123L))
            .willThrow(NotFoundException.class);

        mvc.perform(get(url))
            .andExpect(status().isNotFound());

        then(productService).should().getProductBySku(123L);
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

        then(productService).should().createProduct(request);
    }

    @Test
    public void testPostProductBadRequest() throws Exception {
        String url = "/products";

        ProductRequest request = createProductRequest();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);

        doThrow(SkuAlreadyRegisteredException.class).when(productService).createProduct(request);

        mvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());

        then(productService).should().createProduct(request);
    }

    @Test
    public void testPutProduct() throws Exception {
        String url = "/products";

        ProductRequest request = createProductRequest();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);

        given(productService.updateProduct(request))
            .willReturn(createProductResponse());

        mvc.perform(put(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk());

        then(productService).should().updateProduct(request);
    }

    @Test
    public void testPutProductNotFound() throws Exception {
        String url = "/products";

        ProductRequest request = createProductRequest();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);

        given(productService.updateProduct(any()))
            .willThrow(NotFoundException.class);

        mvc.perform(put(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isNotFound());

        then(productService).should().updateProduct(any());
    }

    @Test
    public void testDeleteProduct() throws Exception {
        String url = "/products/123";

        mvc.perform(delete(url))
            .andExpect(status().isOk());

        then(productService).should().deleteProduct(123L);
    }
}

