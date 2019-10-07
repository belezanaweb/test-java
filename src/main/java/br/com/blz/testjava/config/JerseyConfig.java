package br.com.blz.testjava.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.validation.internal.ValidationExceptionMapper;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.stereotype.Component;

import br.com.blz.testjava.controller.HealthController;
import br.com.blz.testjava.controller.SkuController;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Component
@ApplicationPath("/test-java/v1")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(WadlResource.class);
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);
        register(ValidationExceptionMapper.class);
        register(HealthController.class);
        register(SkuController.class);

        BeanConfig conf = new BeanConfig();
        conf.setTitle("API Sku/Produto");
        conf.setDescription("Teste básico de codigo java");
        conf.setContact("Leonardo Matos - label.matos@live.com");
        conf.setLicense("Beleza na Web - Copyright © 2019 todos os direitos reservados");
        conf.setVersion("v1");
        conf.setBasePath("/test-java/v1");
        conf.setSchemes(new String[]{"http"});
        conf.setResourcePackage("br.com.blz");
        conf.setScan(true);
    }

}
