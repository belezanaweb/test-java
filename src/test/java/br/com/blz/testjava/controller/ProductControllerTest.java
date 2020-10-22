package br.com.blz.testjava.controller;

import br.com.blz.testjava.dto.ProductRequestDTO;
import br.com.blz.testjava.dto.ProductResponseDTO;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testPostProductSuccess() throws Exception {

        InputStream inputStream = ProductControllerTest.class.getResourceAsStream("/create-product-request.json");
        String jsonRequest = convertInputStreamToString(inputStream);


        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
            .post("/product")
            .content(jsonRequest)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andReturn();


        String jsonResponse = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        InputStream responseInputStream = ProductControllerTest.class.getResourceAsStream("/create-product-response.json");
        String jsonExpectedResponse = convertInputStreamToString(responseInputStream);
        Assert.assertEquals(jsonExpectedResponse.trim(), jsonResponse);

        ObjectMapper mapper = new ObjectMapper();
        ProductResponseDTO responseDTO = mapper.readValue(jsonResponse, ProductResponseDTO.class);

        Optional<Product> product = productRepository.findBySku(responseDTO.getSku());
        Assert.assertTrue(product.isPresent());
        Assert.assertEquals(responseDTO, product.get().toResponseDTO());

    }

    @Test
    public void testGetProductSuccess() throws Exception {

        InputStream inputStream = ProductControllerTest.class.getResourceAsStream("/find-product-by-sku-11111-create-request.json");
        String jsonRequest = convertInputStreamToString(inputStream);
        ObjectMapper mapper = new ObjectMapper();
        ProductRequestDTO requestDTO = mapper.readValue(jsonRequest,ProductRequestDTO.class);
        productRepository.save(requestDTO.toEntity());

        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
            .get("/product/11111")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String jsonResponse = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        InputStream responseInputStream = ProductControllerTest.class.getResourceAsStream("/find-product-by-sku-11111-response.json");
        String jsonExpectedResponse = convertInputStreamToString(responseInputStream);
        Assert.assertEquals(jsonExpectedResponse.trim(), jsonResponse);
    }

    @Test
    public void testGetProductNotFound() throws Exception {

        mockMvc.perform( MockMvcRequestBuilders
            .get("/product/99999")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

    }

    @Test
    public void testDeleteProductSuccess() throws Exception {

        InputStream inputStream = ProductControllerTest.class.getResourceAsStream("/delete-product-by-sku-11112-create-request.json");
        String jsonRequest = convertInputStreamToString(inputStream);
        ObjectMapper mapper = new ObjectMapper();
        ProductRequestDTO requestDTO = mapper.readValue(jsonRequest,ProductRequestDTO.class);
        productRepository.save(requestDTO.toEntity());

        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
            .delete("/product/11112")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String jsonResponse = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        InputStream responseInputStream = ProductControllerTest.class.getResourceAsStream("/delete-product-by-sku-11112-response.json");
        String jsonExpectedResponse = convertInputStreamToString(responseInputStream);
        Assert.assertEquals(jsonExpectedResponse.trim(), jsonResponse);

        Optional<Product> product = productRepository.findBySku(requestDTO.getSku());
        Assert.assertFalse(product.isPresent());

    }

    @Test
    public void testDeleteProductNotFound() throws Exception {

        mockMvc.perform( MockMvcRequestBuilders
            .delete("/product/99999")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

    }

    @Test
    public void testPutProductSuccess() throws Exception {

        InputStream createInputStream = ProductControllerTest.class.getResourceAsStream("/update-product-by-sku-11113-create-request.json");
        String jsonCreateRequest = convertInputStreamToString(createInputStream);
        ObjectMapper mapper = new ObjectMapper();
        ProductRequestDTO requestDTO = mapper.readValue(jsonCreateRequest, ProductRequestDTO.class);
        Product productBefore = productRepository.save(requestDTO.toEntity());

        InputStream inputStream = ProductControllerTest.class.getResourceAsStream("/update-product-by-sku-11113-update-request.json");
        String jsonRequest = convertInputStreamToString(inputStream);

        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
            .put("/product/11113")
            .content(jsonRequest)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String jsonResponse = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        InputStream responseInputStream = ProductControllerTest.class.getResourceAsStream("/update-product-by-sku-11113-response.json");
        String jsonExpectedResponse = convertInputStreamToString(responseInputStream);
        Assert.assertEquals(jsonExpectedResponse.trim(), jsonResponse);

        Optional<Product> product = productRepository.findBySku(requestDTO.getSku());
        Assert.assertTrue(product.isPresent());
        Assert.assertEquals(11113l, product.get().getSku().longValue());
        Assert.assertNotEquals(productBefore.toResponseDTO(), product.get().toResponseDTO());



    }

    @Test
    public void testPutProductNotFound() throws Exception {

        InputStream inputStream = ProductControllerTest.class.getResourceAsStream("/update-product-by-sku-11113-update-request.json");
        String jsonRequest = convertInputStreamToString(inputStream);

        mockMvc.perform( MockMvcRequestBuilders
            .put("/product/99999")
            .content(jsonRequest)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name()))))
            {
                int c = 0;
                while((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }
        inputStream.close();
        return textBuilder.toString();
    }
}
