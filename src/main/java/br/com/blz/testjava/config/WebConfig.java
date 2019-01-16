package br.com.blz.testjava.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({"br.com.blz.testjava", "br.com.blz.testjava.service"})
@EntityScan("br.com.blz.testjava.models")
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(WebConfig.class);

    @PostConstruct
    public void init() {
        LOG.debug("init() {} (debug={})", WebConfig.class.getSimpleName(), true);
    }

    @PreDestroy
    public void destroy() {
        LOG.debug("stop() {}", getClass().getSimpleName());
    }
}