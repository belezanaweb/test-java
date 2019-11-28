package br.com.blz.testjava.product;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import br.com.blz.testjava.TestJavaApplication;

@SpringBootTest(classes = TestJavaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class ProductStepDefs {
	
	@Autowired
    private TestRestTemplate template;
	
	private String id;
	
	private ResponseEntity<ProductDTO> response;
	
	@Autowired
	@Mock
	private ProductRepository postRepository;
	
	@Autowired
	@InjectMocks
	private ProductService PostService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	

}
