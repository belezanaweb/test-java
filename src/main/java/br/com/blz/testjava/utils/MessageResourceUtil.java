package br.com.blz.testjava.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Component
public class MessageResourceUtil {

    @Value("${messages.basename:messages}")
    private String basename;

    @Value("${messages.locale.language:}")
    private String localeLanguage;

    @Value("${messages.locale.country:}")
    private String localeCountry;

    private ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    private ResourceBundle reourceBundle;

    private Locale currentLocale =  LocaleContextHolder.getLocale();

    @PostConstruct
    public void init() {
        if (!StringUtils.isEmpty(this.localeLanguage) && !StringUtils.isEmpty(this.localeCountry)) {
            this.currentLocale = new Locale(this.localeLanguage, this.localeCountry);
        }
        this.reourceBundle = ResourceBundle.getBundle(this.basename, this.currentLocale);
        this.messageSource.setBasename(this.basename);
        this.messageSource.setDefaultEncoding("UTF-8");
    }

    public String getMessage(String key, String defaultMessage, Object... args) {
        String defaultMsg = this.reourceBundle.getString(defaultMessage);
        return this.messageSource.getMessage(key, args, defaultMsg, this.currentLocale);
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(this.currentLocale);
        return sessionLocaleResolver;
    }
}
