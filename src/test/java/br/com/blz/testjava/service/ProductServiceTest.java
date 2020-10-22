package br.com.blz.testjava.service;

import br.com.blz.testjava.controller.ProductControllerTest;
import br.com.blz.testjava.dto.ProductRequestDTO;
import br.com.blz.testjava.dto.ProductResponseDTO;
import br.com.blz.testjava.dto.ProductUpdateRequestDTO;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static br.com.blz.testjava.utils.TestUtils.asJsonString;
import static br.com.blz.testjava.utils.TestUtils.convertInputStreamToString;

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

    @Test(expected = ProductNotFoundException.class)
    public void testDeleteProductSuccess() throws IOException, ProductAlreadyExistsException, ProductNotFoundException {
        InputStream inputStream = ProductControllerTest.class.getResourceAsStream("/delete-product-by-sku-11112-create-request.json");
        String jsonRequest = convertInputStreamToString(inputStream);
        ObjectMapper mapper = new ObjectMapper();
        ProductRequestDTO requestDTO = mapper.readValue(jsonRequest,ProductRequestDTO.class);
        ProductResponseDTO createResponseDTO = productService.createProduct(requestDTO);

        ProductResponseDTO deleteResponseDTO = productService.deleteProduct(requestDTO.getSku());

        Assert.assertEquals(createResponseDTO, deleteResponseDTO);
        Optional<Product> product = productRepository.findBySku(requestDTO.getSku());
        Assert.assertFalse(product.isPresent());

        productService.findProductBySku(requestDTO.getSku());
    }

    @Test(expected = ProductNotFoundException.class)
    public void testDeleteProductNotFound() throws ProductNotFoundException {
        ProductResponseDTO deleteResponseDTO = productService.deleteProduct(99999999999l);
    }

    @Test
    public void testUpdateProductSuccess() throws IOException, ProductAlreadyExistsException, ProductNotFoundException {
        InputStream createinputStream = ProductControllerTest.class.getResourceAsStream("/update-product-by-sku-11113-create-request.json");
        String jsonCreateRequest = convertInputStreamToString(createinputStream);
        ObjectMapper mapper = new ObjectMapper();
        ProductRequestDTO createRequestDTO = mapper.readValue(jsonCreateRequest,ProductRequestDTO.class);

        ProductResponseDTO createResponseDTO = productService.createProduct(createRequestDTO);

        InputStream updateInputStream = ProductControllerTest.class.getResourceAsStream("/update-product-by-sku-11113-update-request.json");
        String jsonUpdateRequest = convertInputStreamToString(updateInputStream);
        ProductUpdateRequestDTO updateRequestDTO = mapper.readValue(jsonUpdateRequest, ProductUpdateRequestDTO.class);
        ProductResponseDTO updateResponseDTO = productService.updateProduct(createRequestDTO.getSku(), updateRequestDTO);

        InputStream responseInputStream = ProductControllerTest.class.getResourceAsStream("/update-product-by-sku-11113-response.json");
        String jsonExpectedResponse = convertInputStreamToString(responseInputStream);
        String jsonResponse = asJsonString(updateResponseDTO);

        Assert.assertEquals(jsonExpectedResponse.trim(), jsonResponse);

        ProductResponseDTO responseDTO = productService.findProductBySku(createRequestDTO.getSku());

        Assert.assertEquals(updateResponseDTO, responseDTO);




    }

    @Test(expected = ProductNotFoundException.class)
    public void testUpdateProductNotFound() throws ProductNotFoundException {
        ProductResponseDTO deleteResponseDTO = productService.deleteProduct(99999999999l);
    }



}
