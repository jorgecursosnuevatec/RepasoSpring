package com.jgr.repaso.thymeleaf.interceptores.y.configuracion;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.jgr.repaso.thymeleaf.controller.ControladorPersona;

/**
 * The Class ConfiguraIdioma. para gestionar el idioma
 * https://www.udemy.com/course/universidad-java-especialista-en-java-desde-cero-a-master/learn/lecture/21453402#overview
 */
@Configuration
public class ConfiguraIdioma implements WebMvcConfigurer {
	
	private static final Logger logger = LoggerFactory.getLogger(ConfiguraIdioma.class);

	/**
	 * Locale resolver. por defecto lo ponemos en español en la pagina plantilla
	 * podemos cambiar el lenguaje
	 *
	 * @return the locale resolver
	 */
	@Bean
	public LocaleResolver localeResolver() {
		var slr = new SessionLocaleResolver();
		slr.setDefaultLocale(new Locale("es"));
		return slr;
	}

	/**
	 * Locale change interceptor. para cambiar de idioma de manera dinamica,usamos
	 * este interceptor
	 *
	 * @return the locale change interceptor
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		var lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	/**
	 * Adds the interceptors.
	 *
	 * @param registro the registro
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registro) {
		registro.addInterceptor(localeChangeInterceptor());
	}

}
