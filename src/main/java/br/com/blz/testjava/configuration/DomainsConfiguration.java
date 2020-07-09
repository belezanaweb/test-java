package br.com.blz.testjava.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("br.com.blz.testjava.repository")
public class DomainsConfiguration {
}
