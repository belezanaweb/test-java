package br.com.blz.testjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {

    @Bean(name = "messageSource")
    public ResourceBundleMessageSource getMessageSource() throws Exception {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource(); 
        return resourceBundleMessageSource;
    }
}