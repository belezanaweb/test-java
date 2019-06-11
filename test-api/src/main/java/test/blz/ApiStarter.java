package test.blz;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import test.blz.config.GracefulShutdown;

@SpringBootApplication(exclude={ DataSourceAutoConfiguration.class})
@EnableCaching
@Import({ ApiConfig.class, CoreConfig.class })
public class ApiStarter {

    @Value("${server.graceful-shutdown.timeout-ms}")
    private Long shutdownTimeoutSeconds;

    @Bean
    public GracefulShutdown gracefulShutdown () {
        return new GracefulShutdown(shutdownTimeoutSeconds, TimeUnit.MILLISECONDS);
    }

    public static void main (String[] args) {
        SpringApplication.run(ApiStarter.class, args);
    }

}