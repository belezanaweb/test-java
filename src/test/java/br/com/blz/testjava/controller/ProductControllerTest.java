package br.com.blz.testjava.controller;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductService productService;

    private Product productInsert;

    @Before
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = Test.class.getResourceAsStream("/product_insert.json");
        productInsert = mapper.readValue(inputStream, Product.class);
    }

    @Test
    public void givenProducts_whenGETProducts_thenReturnValidProduct() throws Exception {
        given(productRepository.findById(any())).willReturn(Optional.of(productInsert));

        mvc.perform(get("/v1/product/43264")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void givenProducts_whenGETProduct_thenReturnNotFoundProduct() throws Exception {
        given(productRepository.findById(any())).willReturn(Optional.empty());

        mvc.perform(get("/v1/product/43264")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void givenProducts_whenPOSTProducts_thenReturnValidProduct() throws Exception {
        given(productRepository.save(any())).willReturn(productInsert);

        mvc.perform(post("/v1/product")
            .content(new Gson().toJson(productInsert).toString())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    public void givenProducts_whenPUTProducts_thenReturnValidProduct() throws Exception {
        given(productRepository.findById(any())).willReturn(Optional.empty());

        mvc.perform(put("/v1/product/43264")
            .content(new Gson().toJson(productInsert).toString())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void givenProducts_whenDELETEProducts_thenReturnValidProduct() throws Exception {
        given(productRepository.findById(any())).willReturn(Optional.empty());

        mvc.perform(delete("/v1/product/43264")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.NO_CONTENT.value()));
    }
}
