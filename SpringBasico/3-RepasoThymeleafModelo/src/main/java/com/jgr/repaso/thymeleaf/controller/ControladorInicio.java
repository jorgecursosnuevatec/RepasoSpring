package com.jgr.repaso.thymeleaf.controller;


import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jgr.repaso.thymeleaf.model.Persona;

@Controller
@Slf4j
public class ControladorInicio {
    
	//desde el properties
    @Value("${index.saludo}")
    private String saludo;
    
    @GetMapping("/")
    public String inicio(Model model){
        var mensaje = "Mensaje con Thymeleaf";
        
        var persona = new Persona();
        persona.setNombre("Franciso");
        persona.setApellido("Lorin");
        persona.setEmail("jperez@mail.com");
        persona.setTelefono("55443322");
        
        var persona2 = new Persona();
        persona2.setNombre("Chema");
        persona2.setApellido("Pamundi");
        persona2.setEmail("kgomez@mail.com");
        persona2.setTelefono("13229900");
        
        var persona3 = new Persona();
        persona3.setNombre("Carmelo");
        persona3.setApellido("Coton");
        persona3.setEmail("kgomez@mail.com");
        persona3.setTelefono("13229900");
        
        List<Persona> personas = Arrays.asList(persona,persona2,persona3);
        
        log.info("ejecutando el controlador Spring MVC");
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("saludo", saludo);
        //model.addAttribute("persona", persona);
        model.addAttribute("personas", personas);
        return "index";
    }
}
