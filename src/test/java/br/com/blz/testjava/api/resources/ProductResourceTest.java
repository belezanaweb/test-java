package br.com.blz.testjava.api.resources;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.blz.testjava.api.dtos.InventoryDTO;
import br.com.blz.testjava.api.dtos.ProductDTO;
import br.com.blz.testjava.api.dtos.WarehouseDTO;
import br.com.blz.testjava.exceptions.BusinessException;
import br.com.blz.testjava.model.entities.Inventory;
import br.com.blz.testjava.model.entities.Product;
import br.com.blz.testjava.model.entities.Warehouse;
import br.com.blz.testjava.model.entities.enums.ProductTypeEnum;
import br.com.blz.testjava.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Optional;
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
        ProductDTO productDTO = createNewProductDTO();
        Product savedProduct = createNewProduct();
        savedProduct.setSku(45632L);

        BDDMockito.given(productService.save(Mockito.any(Product.class))).willReturn(savedProduct);
        String content = new ObjectMapper().writeValueAsString(productDTO);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(PRODUCT_API)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(content);

        mvc.perform(request)
            .andExpect(status().isCreated())
            .andExpect(jsonPath("sku").value("45632"))
            .andExpect(jsonPath("name").value(productDTO.getName()))
            .andExpect(jsonPath("inventory").isNotEmpty())
            .andExpect(jsonPath("inventory.warehouses").isNotEmpty())
            .andExpect(jsonPath("inventory.warehouses[0].locality").value("SP"))
            .andExpect(jsonPath("inventory.warehouses[0].quantity").value(12))
            .andExpect(jsonPath("inventory.warehouses[0].type").value("ECOMMERCE"))
            .andExpect(jsonPath("inventory.warehouses[1].locality").value("MOEMA"))
            .andExpect(jsonPath("inventory.warehouses[1].quantity").value(3))
            .andExpect(jsonPath("inventory.warehouses[1].type").value("PHYSICAL_STORE"));
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
    public void createDuplicateProductTest() throws Exception {
        ProductDTO productDTO = createNewProductDTO();
        String content = new ObjectMapper().writeValueAsString(productDTO);
        String errorMessage = "SKU already used by other product.";
        BDDMockito.given(productService.save(Mockito.any(Product.class)))
            .willThrow(new BusinessException(errorMessage));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(PRODUCT_API)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(content);

        mvc.perform(request)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("errors", hasSize(1)))
            .andExpect(jsonPath("errors[0]").value(errorMessage));
    }

    @Test
    @DisplayName("Should get a product by SKU")
    public void getProductBySkuTest() throws Exception {
        Long sku = 1L;
        Product product = createNewProduct();
        product.setSku(sku);
        BDDMockito.given(productService.getBySku(sku)).willReturn(Optional.of(product));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .get(PRODUCT_API.concat("/" + sku))
            .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("sku").value(sku))
            .andExpect(jsonPath("name").value(product.getName()))
            .andExpect(jsonPath("inventory").isNotEmpty())
            .andExpect(jsonPath("inventory.warehouses").isNotEmpty())
            .andExpect(jsonPath("inventory.warehouses[0].locality").value("SP"))
            .andExpect(jsonPath("inventory.warehouses[0].quantity").value(12))
            .andExpect(jsonPath("inventory.warehouses[0].type").value("ECOMMERCE"))
            .andExpect(jsonPath("inventory.warehouses[1].locality").value("MOEMA"))
            .andExpect(jsonPath("inventory.warehouses[1].quantity").value(3))
            .andExpect(jsonPath("inventory.warehouses[1].type").value("PHYSICAL_STORE"));
    }

    @Test
    @DisplayName("Should return resource not found when the product doesn't exists")
    public void productNotFoundTest() throws Exception {
        BDDMockito.given(productService.getBySku(Mockito.anyLong())).willReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .get(PRODUCT_API.concat("/" + 1))
            .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(status().isNotFound());
    }

    private ProductDTO createNewProductDTO() {
        WarehouseDTO warehouseDTO1 = WarehouseDTO.builder().locality("SP").quantity(12)
            .type(ProductTypeEnum.ECOMMERCE).build();

        WarehouseDTO warehouseDTO2 = WarehouseDTO.builder().locality("MOEMA").quantity(3)
            .type(ProductTypeEnum.PHYSICAL_STORE).build();

        InventoryDTO inventoryDTO = InventoryDTO.builder()
            .warehouses(Arrays.asList(warehouseDTO1, warehouseDTO2)).build();

        return ProductDTO.builder().name("L'Oréal Professionnel")
            .inventory(inventoryDTO).build();
    }

    private Product createNewProduct() {
        Warehouse warehouse1 = Warehouse.builder().locality("SP").quantity(12)
            .type(ProductTypeEnum.ECOMMERCE).build();

        Warehouse warehouse2 = Warehouse.builder().locality("MOEMA").quantity(3)
            .type(ProductTypeEnum.PHYSICAL_STORE).build();

        Inventory inventory = Inventory.builder()
            .warehouses(Arrays.asList(warehouse1, warehouse2)).build();

        return Product.builder().name("L'Oréal Professionnel")
            .inventory(inventory).build();
    }

}
