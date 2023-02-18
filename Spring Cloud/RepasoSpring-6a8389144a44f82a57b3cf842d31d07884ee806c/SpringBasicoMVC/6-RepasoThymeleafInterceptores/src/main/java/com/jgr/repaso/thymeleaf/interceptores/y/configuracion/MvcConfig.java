package com.jgr.repaso.thymeleaf.interceptores.y.configuracion;

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
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);
	
	/** The interceptor. */
	/**inyectamos TiempoTranscurridoInterceptor */
	@Autowired
	@Qualifier("tiempoTranscurridoInterceptor")
	private HandlerInterceptor tiempoTranscurridoInterceptor;

	
	/** añadimos el interceptor de horario*/
	@Autowired
	@Qualifier("horario")
	private HandlerInterceptor horarioInterceptor;
	
	/**
	 * Adds the interceptors.
	 * añadimos los interceptores para que calcule el tiempo transcurrido en una ejecucion y el
	 * de horario abierto/cerrado
	 *
	 * @param registry the registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		logger.info(logger.getName() + "***ejecutando el controlador MvcConfig****");
		registry.addInterceptor(tiempoTranscurridoInterceptor)
		//.addPathPatterns("/guardar/**","/agregar/**","/eliminar/**");//rutas en las que se va a aplicar
		;
		
		//le hacemos el exclude porque si no daria error,se redirige a si mismo
		registry.addInterceptor(horarioInterceptor).excludePathPatterns("/cerrado");
	}

}
