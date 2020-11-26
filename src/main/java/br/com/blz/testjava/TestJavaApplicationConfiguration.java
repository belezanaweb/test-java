package br.com.blz.testjava;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = {TestJavaApplicationConfiguration.BASE_PACKAGE})
public class TestJavaApplicationConfiguration {

    public static final String BASE_PACKAGE = "br.com.blz.testjava";

}
