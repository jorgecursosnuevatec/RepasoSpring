package com.jgr.repaso.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jgr.repaso.thymeleaf.model.Persona;
import com.jgr.repaso.thymeleaf.servicio.IPersonaServicio;
import com.jgr.repaso.thymeleaf.servicio.PersonaServicioImpl;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


/**
 * The Class ControladorPersona.
 * INCLUIMOS UN INTERCEPTOR PARA QUE CALCULE EL TIEMPO DE EJECUCION 
 * OTRO INTERCEPTOR QUE ES EL QUE INYECTAMOS CON VALUE Y EL HORARIOINTERCEPTOR PARA QUE 
 * DEPENDIENDO DE LA HORA PERMITAMOS EJECUTAR O NO EL PROGRAMA
 * 
 * 
 */

/** The Constant log. */
@Slf4j
@Controller

public class ControladorPersona {
	/*
	 * RECUPERA EL VALOR DE index.saludo que esta en el properties //desde el
	 * properties
	 * 
	 * @Value("${index.saludo}") private String saludo;
	 */

	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(ControladorPersona.class);

	/** The i persona servicio. */
	@Autowired
	private IPersonaServicio iPersonaServicio = new PersonaServicioImpl();

	/** The lista personas. */
	private List<Persona> listaPersonas;
	
	/** The apertura.  LO GUARDAMOS EN EL PROPERTIES
	 * ver uso en horariointerceptor-mvconfig*/
	@Value("${config.horario.apertura}")
	private Integer apertura;
	
	/** The cierre.   LO GUARDAMOS EN EL PROPERTIES
	 * ver uso en horariointerceptor-mvconfig*/
	@Value("${config.horario.cierre}")
	private Integer cierre;
	

	/**
	 * Carga datos.
	 */
	@PostConstruct
	public void cargaDatos() {

		Persona per;
		listaPersonas = new ArrayList<Persona>();

		for (int i = 0; i < 5; i++) {
			per = new Persona();
			per.setNombre("Nombre" + i);
			per.setApellido("Apellido" + i);
			per.setEmail("email" + i + "@mail.com");
			per.setTelefono("+34" + new Random().nextInt());
			listaPersonas.add(per);
		}
		// iPersonaServicio.altaPersona(per)
		listaPersonas.forEach(iPersonaServicio::altaPersona);

	}

	/**
	 * Inicio.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping({"/", "/index"})
	public String inicio(Model model) {
		log.info(log.getName() + "***ejecutando el controlador ControladorPersona****");
		// iPersonaRepositorio.findAll();
		model.addAttribute("personas", iPersonaServicio.listarPersonas());
		return "index";
	}
	
	/**
	 * Alta persona.
	 *
	 * @param per the per
	 * @return the string
	 */
	@GetMapping({"/agregar","/alta"})
	public String altaPersona(Persona per) {
		return "modificar";
	}
	
	
	/**
	 * Eliminar persona.
	 *
	 * @param per the per
	 * @return the string
	 */
//	@GetMapping({"/eliminar/{idPersona}","/borra"})
	 @GetMapping("/eliminar")
	public String eliminarPersona(Persona per) {
		iPersonaServicio.borrarPersona(per);
		return "redirect:/";
	}
	
	/**
	 * Guardar persona.
	 *
	 * @param per the per
	 * @param errores the errores
	 * @return the string
	 */
	@PostMapping("/guardar")
	public String guardarPersona(@Valid Persona per,Errors errores) {
		
		if(errores.hasErrors()) {
			errores.getFieldErrors().forEach(e->log.error(e.getField()));
			return "modificar";
			
		}
		
		iPersonaServicio.altaPersona(per);
		return "redirect:/";
		
	}
		
	/**
	 * Editar persona.
	 *
	 * @param per the per
	 * @param model the model
	 * @return the string
	 * spring moveria el idPersona a Persona
	 */
	@GetMapping("/editar/{idPersona}")
	public String editarPersona(Persona per,Model model) {		
		
		Optional<Persona> pers=iPersonaServicio.buscaPersona(per);
		
		if (pers.isPresent()) {			
			model.addAttribute("persona",pers.get());			
		}
		return "modificar";
		
	}
	
	/**
	 * Cerrado.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/cerrado")
	public String cerrado(Model model) {
		
		StringBuilder mensaje = new StringBuilder("Cerrado, por favor visítenos desde las ");
		mensaje.append(apertura);
		mensaje.append(" y las ");
		mensaje.append(cierre);
		mensaje.append(" hrs. Gracias.");
		mensaje.append(" REVISA EL HORARIO DEL PROPERTIES");
		
		model.addAttribute("titulo", "Fuera del horario de atención");
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("horario", "cerradp hasta "+ cierre.toString());
		return "index";
	}
}
