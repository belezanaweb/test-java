package br.com.blz.testjava;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication(scanBasePackageClasses = TestJavaApplication.class)
public class TestJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestJavaApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
        Locale.setDefault(new Locale("PT", "br"));
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        return slr;
    }

}
