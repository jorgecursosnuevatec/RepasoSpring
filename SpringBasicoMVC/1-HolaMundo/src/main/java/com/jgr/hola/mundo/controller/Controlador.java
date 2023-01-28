package com.jgr.hola.mundo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class Controlador {
	
	
	@GetMapping({"/inicio","/"})
	public String inicio() {
		log.info("En RestController");
		log.debug("detalle desde el controller");
		return "Hola Mundo";
	}

}
