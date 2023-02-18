package com.jgr.errors;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class ErrorHandlerController {
	
	@ExceptionHandler(IdNoEncontradoException.class)
	public String idNoEncontrado(IdNoEncontradoException ex, Model model) {
		model.addAttribute("error", "Error: ID no encontrado!");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		return "error/error";
	}
	
	@ExceptionHandler(GeneroExisteException.class)
	public String GeneroExiste(GeneroExisteException ex, Model model) {
		model.addAttribute("error", "Error: Genero ya existe en el sistema!");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		return "error/error";
	}
	
	@ExceptionHandler(TituloExisteException.class)
	public String TituloExiste(TituloExisteException ex, Model model) {
		model.addAttribute("error", "Error: Pelicula ya existe en el sistema!");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		return "error/error";
	}
	
	
	@ExceptionHandler(ErrorBBDDException.class)
	public String ErrornBBDD(ErrorBBDDException ex, Model model) {
		model.addAttribute("error", "Error: en acceso a BBDD");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		return "error/error";
	}
	
}
