package com.jgr.repaso.thymeleaf.interceptores;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The Class TiempoTranscurridoInterceptor.
 * 
 * para ver cuanto tarda la peticion
 * como component para inyectarlo donde queramos,lo vamos a hacer en mvConfig,le damos
 * un nombre para que cuando inyectemos interceptores podamos especificar
 * https://www.udemy.com/course/master-completo-java-de-cero-a-experto/learn/lecture/23731950#overview
 */
@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor{
	
	private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

	/**
	 * Pre handle.
	 * Cuando retorna true consinua con la ejecucion,si hay mas interceptores se ejecutan
	 * si devuelve FALSE se encarga de la peticion y no continua ejecutando el controlador
	 * ni los demas interceptores
	 * 
	 * en esta clase lo que vamos a hacer es ver cuanto tarda en ejecutarse una peticion
	 * para ello guardamos en el request el tiempo de inicio y luego en el posthandle calculamos lo que 
	 * ha tardado
	 *
	 * @param request the request
	 * @param response the response
	 * @param handler the handler
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		//si es un metodo del controlador
		if(handler instanceof HandlerMethod) {
			HandlerMethod metodo = (HandlerMethod) handler;
		
		logger.info("*****************TiempoTranscurridoInterceptor preHandle entrando desde ruta->"
				+metodo.getMethod().getName());
		logger.info("*****************TiempoTranscurridoInterceptor preHandle Handler->"+handler);
		}
		
		//guardo el inicio
		long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempo inicio", tiempoInicio);
		
		//simulamos demora aleatoria
		Random aleatorio = new Random();
		int retraso = aleatorio.nextInt(500);
		Thread.sleep(retraso);
		
		return true;
	}

	/**
	 * Post handle.
	 * implementa algo despues de que se haya invoado el handler del controlador
	 * aqui recuperamos el tiempo de inicio que hemos cargado en el prehandle
	 * y pasamos el resultado al ModelAndView
	 * @param request the request
	 * @param response the response
	 * @param handler the handler
	 * @param modelAndView the model and view
	 * @throws Exception the exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		long tiempoFinal = System.currentTimeMillis();
		long tiempoInicio=(Long)request.getAttribute("tiempo inicio");		
		long tiempoTranscurrido = tiempoFinal-tiempoInicio;
		
		if(handler instanceof HandlerMethod && modelAndView!=null) {
//			if(modelAndView!=null) {
			modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);			
		}
		
		
		HandlerMethod metodo = (HandlerMethod) handler;
		
		logger.info("*****************TiempoTranscurridoInterceptor postHandle saliendo->"+metodo.getMethod().getName());
		logger.info("*****************TiempoTranscurrido->"+tiempoTranscurrido);
		
	}

	/**
	 * After completion.
	 * implementa algo cuando finaliza el proceso, despues de cargar y renderizar la vista
	 * @param request the request
	 * @param response the response
	 * @param handler the handler
	 * @param ex the ex
	 * @throws Exception the exception
	 
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	*/
	
	
}
