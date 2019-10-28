package br.com.blz.testjava;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.blz.testjava.resource.ProductResource;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductResource.class)

public class TestJavaApplicationTests {

    @Autowired
    private MockMvc mockMvc;
 
    
    @Test()
    public void whenTestMvcController_thenRetrieveExpectedResult() throws Exception {
        // ...
 
      //  this.mockMvc.perform(get("/products")
          //  .andExpect(...);
    }
    
	@Test
	public void contextLoads() {
	}

}
