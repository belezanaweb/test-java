package br.com.blz.testjava.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class responsible for configuring the application's data cache.
 * 
 * @author Andre Barroso
 *
 */
@Configuration
@EnableCaching
public class ProductConfiguration {

	/**
	 * Method responsible for loading the chache manager.
	 * 
	 * @return The chache manager.
	 */
	@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("products");
    }
	
}
