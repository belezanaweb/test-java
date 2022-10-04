package br.com.blz.testjava.bdd;

import br.com.blz.testjava.TestJavaApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = TestJavaApplication.class, loader = SpringBootContextLoader.class)
@AutoConfigureMockMvc
public class CucumberSteps {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected TestRestTemplate template;

    @Value("http://localhost")
    protected String host;

    @Value("/products")
    protected String basePath;

    @LocalServerPort
    protected Long port;

}
