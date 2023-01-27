package com.jgr.repaso.thymeleaf.interceptores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




/**
 * The Class MvcConfig.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
	private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);
	
	/** The interceptor. */
	/**inyectamos TiempoTranscurridoInterceptor */
	@Autowired
	@Qualifier("tiempoTranscurridoInterceptor")
	private HandlerInterceptor tiempoTranscurridoInterceptor;

	/**
	 * Adds the interceptors.
	 *
	 * @param registry the registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		logger.info(logger.getName() + "***ejecutando el controlador MvcConfig****");
		registry.addInterceptor(tiempoTranscurridoInterceptor)
		//.addPathPatterns("/guardar/**","/agregar/**","/eliminar/**");//rutas en las que se va a aplicar
		;
	}

}
