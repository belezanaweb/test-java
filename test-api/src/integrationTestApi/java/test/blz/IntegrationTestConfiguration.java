package test.blz;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ CoreConfig.class })
public class IntegrationTestConfiguration {

}
