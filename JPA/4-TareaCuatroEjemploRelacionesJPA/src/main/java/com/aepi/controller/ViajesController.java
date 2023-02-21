package com.aepi.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.aepi.model.Viajero;
import com.aepi.model.Asiento;
import com.aepi.model.Tren;
import com.aepi.model.Vagon;
import com.aepi.services.ViajesService;

@Controller
public class ViajesController {

	@Autowired
	private ViajesService service;

	@GetMapping("/trenes")
	public String trenes(Model model) {

		model.addAttribute("trenes", (List<Tren>) service.getTrenes());
		return "trenes";
	}

	@GetMapping("/tren/{id}/viajeros")
	public String viajeros(@PathVariable("id") Integer id, Model model) {

		Optional<Tren> tren = service.getTren(id);

		if (tren != null) {
			model.addAttribute("viajeros", tren.get().getViajeros());
		}
		return "viajeros";
	}

	@GetMapping("/addViajero/tren/{id}")
	public String addViajeros(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("idTren", id);
		model.addAttribute("viajero", new Viajero());
		return "viajeros";
	}

	@PostMapping("/addViajero/tren/{id}")
	public String addViajero(@Valid Viajero viajero, BindingResult result, 
							@PathVariable("id") Integer idTren,
							Model model) 
	{
		if (result.hasErrors()) 
		{
			model.addAttribute("idTren", idTren);
			model.addAttribute("viajero", viajero);
			return "viajeros";
		}
		
		Optional<Tren> tren = service.getTren(idTren);

		if (tren != null) {
			
			// almacenar y recuperar su ID
			viajero = service.save(new Viajero(null, viajero.getNombre(), viajero.getApellidos(), viajero.getDni()));			
			
			// relacionar con el tren
			tren.get().addViajero(viajero);
			service.update(tren.get());
		}
		return "redirect:/trenes";
	}
	
	// vagones
	
	@GetMapping("/tren/{id}/vagones")
	public String vagones(@PathVariable("id") Integer id, Model model) {

		Optional<Tren> tren = service.getTren(id);

		if (tren != null) {
			model.addAttribute("vagones", tren.get().getVagones());
		}
		return "vagones";
	}

	@GetMapping("/addVagon/tren/{id}")
	public String addVagones(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("idTren", id);
		model.addAttribute("vagon", new Vagon());
		return "vagones";
	}

	@PostMapping("/addVagon/tren/{id}")
	public String addVagon(@Valid Vagon vagon, BindingResult result, 
							Integer numAsientos,
							@PathVariable("id") Integer idTren,
							Model model) 
	{
		if (result.hasErrors()) 
		{
			model.addAttribute("idTren", idTren);
			model.addAttribute("vagon", vagon);
			return "vagones";
		}
		
		Optional<Tren> tren = service.getTren(idTren);

		if (tren != null) {
			// insertar el nuevo vagon
			Vagon vagon2 = new Vagon(null, vagon.getCategoria());
			vagon2.setTren(tren.get());			
			vagon = service.save(vagon2);	
			
			// crear e insertar los asientos
			for (int i = 0; i < numAsientos; i++) {
				Asiento asiento = new Asiento();
				asiento.setVagon(vagon);	// relacionar con su vagon que tiene ID
				asiento = service.save(asiento);
			}	
			
			// relacionar el vagon con el tren
			tren.get().addVagon(vagon);
			service.update(tren.get());
		}
		return "redirect:/trenes";
	}

	@PostConstruct
	private void llenarDB() {

		// Crear los trenes
		for (int i = 1; i <= 5; i++) {
			service.save(new Tren(null, "matricula" + i));
		}
	}
}
