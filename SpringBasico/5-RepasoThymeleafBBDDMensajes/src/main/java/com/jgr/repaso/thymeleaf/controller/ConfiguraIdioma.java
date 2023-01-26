package com.jgr.repaso.thymeleaf.controller;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


/**
 * The Class ConfiguraIdioma.
 * para gestionar el idioma
 */
@Configuration
public class ConfiguraIdioma implements WebMvcConfigurer{
	
	 /**
 	 * Locale resolver.
 	 * por defecto lo ponemos en español
 	 * en la pagina plantilla podemos cambiar el lenguaje
 	 *
 	 * @return the locale resolver
 	 */
 	@Bean
	 public LocaleResolver localeResolver(){
	        var slr = new SessionLocaleResolver();
	        slr.setDefaultLocale(new Locale("es"));
	        return slr;
	    }
	    
	    /**
    	 * Locale change interceptor.
    	 * para cambiar de idioma de manera dinamica,usamos este interceptor
    	 *
    	 * @return the locale change interceptor
    	 */
    	@Bean
	    public LocaleChangeInterceptor localeChangeInterceptor(){
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
	    public void addInterceptors(InterceptorRegistry registro){
	        registro.addInterceptor(localeChangeInterceptor());
	    }
	    
	}

