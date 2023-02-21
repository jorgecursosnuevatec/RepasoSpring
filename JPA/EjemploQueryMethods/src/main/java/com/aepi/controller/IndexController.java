package com.aepi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.aepi.entity.Persona;
import com.aepi.repository.PersonaRepository;

@Controller
public class IndexController {
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@GetMapping({"/", "", "/index"})
	public String index(Model model) {
		model.addAttribute("persona", new Persona());
		return "index";
	}
	
	@PostMapping("/nombres")
	public String nombres(@Valid Persona persona, Model model) {
		model.addAttribute("personas", personaRepository.getPersonasPorNombre(persona.getNombre()));
		return "nombres";
	}
	
	@PostMapping("/personas")
	public String personas(@Valid Persona persona, Model model) {
		model.addAttribute("personas", personaRepository.getAllPersonas());		
		return "personas";
	}
	
	@PostMapping("/posicionales")
	public String posicionales(@Valid Persona persona, Model model) {
		model.addAttribute("personas", personaRepository.getDatosPosicionales(persona.getId(), persona.getSalario()));		
		return "posicionales";
	}
}
