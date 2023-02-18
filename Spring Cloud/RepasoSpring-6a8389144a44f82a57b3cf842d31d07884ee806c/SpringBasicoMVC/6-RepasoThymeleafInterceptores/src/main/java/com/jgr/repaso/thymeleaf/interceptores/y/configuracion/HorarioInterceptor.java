package com.jgr.repaso.thymeleaf.interceptores.y.configuracion;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * The Class HorarioInterceptor.
 * Se recogen los parametros del properties y dependiendo de la hora dejamos que se cargue o no la aplicacion
 * Lo definimos como componente y lo cargamos con el MvcConfig
 * https://www.udemy.com/course/master-completo-java-de-cero-a-experto/learn/lecture/23731994#overview
 * 
 */
@Component("horario")
public class HorarioInterceptor implements HandlerInterceptor {
	
	/** The apertura. */
	@Value("${config.horario.apertura}")
	private Integer apertura;
	
	/** The cierre. */
	@Value("${config.horario.cierre}")
	private Integer cierre;
	
	private static final Logger logger = LoggerFactory.getLogger(HorarioInterceptor.class);

	/**
	 * Pre handle.
	 * validamos que la hora este en un rango para que pueda entrar o no
	 * las variables config.horario.* las hemos definido en el properties
	 * este metodo realiza esa validacion y luego en el MvcConfig registramos
	 * el controlador
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
			logger.info("*****************HorarioInterceptor preHandle entrando desde ruta->"
					+metodo.getMethod().getName());
		}

		
		
		Calendar calendar = Calendar.getInstance();
		int hora = calendar.get(Calendar.HOUR_OF_DAY);
		StringBuilder mensaje;
		
		if(hora >= apertura && hora < cierre) {			
			mensaje = new StringBuilder("Bienvenidos al horario de atencion a clientes");
			mensaje.append(", atendemos desde las ");
			mensaje.append(apertura);
			mensaje.append("hrs. ");
			mensaje.append("hasta las ");
			mensaje.append(cierre);
			mensaje.append("hrs. ");
			mensaje.append("Gracias por su visita.");
			request.setAttribute("mensaje", mensaje.toString());
			return true;
		}
//		mensaje = new StringBuilder("Estamos cerrados hasta las");
//		mensaje.append(cierre.toString());
//		request.setAttribute("mensaje", mensaje.toString());
		response.sendRedirect(request.getContextPath().concat("/cerrado"));
		return false;
	}

	/**
	 * Post handle.
	 *
	 * @param request the request
	 * @param response the response
	 * @param handler the handler
	 * @param modelAndView the model and view
	 * @throws Exception the exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String mensaje = (String) request.getAttribute("mensaje");
		
		if(modelAndView != null && handler instanceof HandlerMethod) {
		    modelAndView.addObject("horario", mensaje);
		}
//		modelAndView.addObject("mensaje", mensaje);
	}

}
