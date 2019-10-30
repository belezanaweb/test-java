package br.com.blz.testjava;

import br.com.blz.testjava.controller.ProductController;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class TestProductApi {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService service;

    @Test
    public void product_get_api()
        throws Exception {

        Product product = DataSeed.createProduct();

        given(service.getOne(product.getSku())).willReturn(product);

        mvc.perform(get("/api/v1/products/" + product.getSku())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(product.getName())))
            .andExpect(jsonPath("$.marketable", is(true)))
            .andExpect(jsonPath("$.inventary.quantity", is(15)))
            .andExpect(jsonPath("$.inventary.warehouses", hasSize(2)))
            .andExpect(jsonPath("$.inventary.warehouses[0].locality", is("SP")))
            .andExpect(jsonPath("$.inventary.warehouses[0].quantity", is(12)))
            .andExpect(jsonPath("$.inventary.warehouses[0].type", is("ECOMMERCE")))
            .andExpect(jsonPath("$.inventary.warehouses[1].quantity", is(3)));
    }

    @Test
    public void product_post_api()
        throws Exception {

        Product product = DataSeed.createProduct();

        doNothing().when(service).save(product);

        mvc.perform(post("/api/v1/products")
            .content(new ObjectMapper().writeValueAsString(product))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.sku", is(product.getSku())));
    }

    @Test
    public void product_delete_api()
        throws Exception {
        Integer sku = 123;
        doNothing().when(service).delete(sku);

        mvc.perform(delete("/api/v1/products/" + sku)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

}
