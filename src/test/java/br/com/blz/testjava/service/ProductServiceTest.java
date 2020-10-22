package br.com.blz.testjava.service;

import br.com.blz.testjava.controller.ProductControllerTest;
import br.com.blz.testjava.dto.ProductRequestDTO;
import br.com.blz.testjava.dto.ProductResponseDTO;
import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductService.class, ProductRepository.class})
public class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void testCreateProductSuccess() throws IOException, ProductAlreadyExistsException {
        InputStream inputStream = ProductControllerTest.class.getResourceAsStream("/create-product-request.json");
        String jsonRequest = convertInputStreamToString(inputStream);
        ObjectMapper mapper = new ObjectMapper();
        ProductRequestDTO requestDTO = mapper.readValue(jsonRequest,ProductRequestDTO.class);
        ProductResponseDTO responseDTO = productService.createProduct(requestDTO);

        String jsonResponse = asJsonString(responseDTO);

        InputStream responseInputStream = ProductControllerTest.class.getResourceAsStream("/create-product-response.json");
        String jsonExpectedResponse = convertInputStreamToString(responseInputStream);
        Assert.assertEquals(jsonExpectedResponse.trim(), jsonResponse);

        Optional<Product> product = productRepository.findBySku(responseDTO.getSku());
        Assert.assertTrue(product.isPresent());
        Assert.assertEquals(responseDTO, product.get().toResponseDTO());
    }

    @Test(expected = ProductAlreadyExistsException.class)
    public void testCreateProductAlreadyExists() throws IOException, ProductAlreadyExistsException {
        InputStream inputStream = ProductControllerTest.class.getResourceAsStream("/create-product-request.json");
        String jsonRequest = convertInputStreamToString(inputStream);
        ObjectMapper mapper = new ObjectMapper();
        ProductRequestDTO requestDTO = mapper.readValue(jsonRequest,ProductRequestDTO.class);
        ProductResponseDTO responseDTO1 = productService.createProduct(requestDTO);
        ProductResponseDTO responseDTO2 = productService.createProduct(requestDTO);
    }

    @Test
    public void testFindProductBySkuSuccess() throws IOException, ProductAlreadyExistsException, ProductNotFoundException {
        InputStream inputStream = ProductControllerTest.class.getResourceAsStream("/find-product-by-sku-11111-create-request.json");
        String jsonRequest = convertInputStreamToString(inputStream);
        ObjectMapper mapper = new ObjectMapper();
        ProductRequestDTO requestDTO = mapper.readValue(jsonRequest,ProductRequestDTO.class);
        productService.createProduct(requestDTO);

        ProductResponseDTO responseDTO = productService.findProductBySku(requestDTO.getSku());

        String jsonResponse = asJsonString(responseDTO);

        InputStream responseInputStream = ProductControllerTest.class.getResourceAsStream("/find-product-by-sku-11111-response.json");
        String jsonExpectedResponse = convertInputStreamToString(responseInputStream);
        Assert.assertEquals(jsonExpectedResponse.trim(), jsonResponse);


    }

    @Test(expected = ProductNotFoundException.class)
    public void testFindProductBySkuNotFound() throws ProductNotFoundException {
        ProductResponseDTO responseDTO = productService.findProductBySku(9999999l);
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
