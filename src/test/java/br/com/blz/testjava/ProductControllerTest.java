package br.com.blz.testjava;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.controller.ProductController;
import br.com.blz.testjava.models.Inventory;
import br.com.blz.testjava.models.Product;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {
	
	private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository mockRepository;

    @Before
    public void init() {
    	Inventory inventory = new Inventory();
        Product product = new Product(1, "Product Name", inventory);
        when(mockRepository.findProduct(1)).thenReturn(Optional.of(product));
    }
}
