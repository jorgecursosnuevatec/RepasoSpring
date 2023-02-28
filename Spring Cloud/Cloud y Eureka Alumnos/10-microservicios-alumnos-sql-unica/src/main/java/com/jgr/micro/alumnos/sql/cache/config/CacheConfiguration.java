package com.jgr.micro.alumnos.sql.cache.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO: Auto-generated Javadoc
/**
 * The Class CacheConfiguration.
 * Clase de configuracion para gestion del cache
 */
@EnableCaching
@Configuration
public class CacheConfiguration {
	
	/**
	 * Gets the manager.
	 *
	 * @return the manager
	 */
	@Bean
	public CacheManager getManager() {
		return new ConcurrentMapCacheManager("guardaCache");
		
	}

}
