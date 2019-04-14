package br.com.blz.testjava.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:config.properties")
@Data
public class Configs {

    @Value("${message.create}")
    private String messageCreate;

    @Value("${message.update}")
    private String messageUpdate;
}
