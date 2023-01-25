package com.jgr.repaso.thymeleaf.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jgr.repaso.thymeleaf.model.Persona;
import com.jgr.repaso.thymeleaf.servicio.IPersonaServicio;
import com.jgr.repaso.thymeleaf.servicio.PersonaServicioImpl;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;



/**
 * The Class ControladorPersona.
 */
@Slf4j
@Controller


public class ControladorPersona {
    /*
     * RECUPERA EL VALOR DE index.saludo que esta en el properties
	//desde el properties
    @Value("${index.saludo}")
    private String saludo;
   */ 
	
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(ControladorPersona.class);
	
	@Autowired
	private  IPersonaServicio iPersonaServicio= new PersonaServicioImpl();
	
	/** The lista personas. */
    private List<Persona> listaPersonas;
	
	/**
	 * Carga datos.
	 */
	@PostConstruct
	public void cargaDatos() {
		
		Persona per;
		listaPersonas = new ArrayList<Persona>();
		
		for (int i=0;i<5;i++) {
			per = new Persona();
			per.setNombre("Nombre"+i);
			per.setApellido("Apellido"+i);
			per.setEmail("email"+i+"@mail.com");
			per.setTelefono("+34"+new Random().nextInt());
			listaPersonas.add(per);
		}
		//iPersonaServicio.altaPersona(per)
		listaPersonas.forEach(iPersonaServicio::altaPersona);
		
		
	}
	
    /**
     * Inicio.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/")
    public String inicio(Model model){
        log.info(log.getName()+"***ejecutando el controlador Spring MVC****");
        
        //iPersonaRepositorio.findAll();
        model.addAttribute("personas",iPersonaServicio.listarPersonas());
        
        return "index";
    }
}
