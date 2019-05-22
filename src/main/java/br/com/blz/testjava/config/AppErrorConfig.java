package br.com.blz.testjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.MessageSource;

@Configuration
public class AppErrorConfig {

	@Bean
	public MessageSource apiErrorMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/errors");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
