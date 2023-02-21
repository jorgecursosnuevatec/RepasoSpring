package com.aepi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.aepi.entities.Alumno;
import com.aepi.entities.Clase;
import com.aepi.services.IServiceDb;


@Controller
@SessionAttributes({ "alumno", "clase"})
@RequestMapping("/alumno")
public class AlumnoController {

	@Autowired
	private IServiceDb serviceDb;
	
	@GetMapping("/lista")
	public String lista(Model model) {
		model.addAttribute("alumnos", serviceDb.findAllAlumnos());
		model.addAttribute("titulo","Lista de alumnos");
		return "/alumno/lista";
	}
	
	@GetMapping("/agregar")
	public String agregar(Model model) {
		model.addAttribute("alumno", new Alumno());
		model.addAttribute("titulo","Agregar un alumno");
		return "/alumno/agregar";
	}
	
	@PostMapping("/agregar")
	public String agregar(@Valid Alumno alumno, BindingResult result ,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Agregar alumno (Campos incorrectos)");
			return "/alumno/agregar";
		}
		
		serviceDb.saveAlumno(alumno);
		model.addAttribute("titulo","Resultado");
		return "redirect:/alumno/lista";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		
		model.addAttribute("alumno", serviceDb.findByIdAlumno(id)) ;
		model.addAttribute("titulo","Editar alumno");
		return "/alumno/editar";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Alumno alumno, BindingResult result ,Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Editar alumno (Campos incorrectos)");
			return "/alumno/editar";
		}
		serviceDb.saveAlumno(alumno);
		status.setComplete();
		return "redirect:/alumno/lista";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id ,Model model) {
		serviceDb.deleteAlumno(serviceDb.findByIdAlumno(id));
		return "redirect:/alumno/lista";
	}
	
	@GetMapping("/{id}/listaclases")
	public String menus(@PathVariable Integer id ,Model model) {
		model.addAttribute("titulo","Lista de clases");
		model.addAttribute("alumnoId",id);
		model.addAttribute("clases", serviceDb.findByIdAlumno(id).getClases());
		return "/alumno/listaclases";
	}
	
	@GetMapping("/{id}/agregarclase")
	public String agregarClase(@PathVariable Integer id, Model model) {
		Clase clase = new Clase();
		model.addAttribute("clase", clase);
		model.addAttribute("alumnoId", id);
		model.addAttribute("titulo","Agregar clase");
		return "/alumno/agregarclase";
	}
	
	@PostMapping("/{id}/agregarclase")
	public String agregarClase(@PathVariable Integer id, @Valid Clase clase, BindingResult result ,Model model) {
		model.addAttribute("alumnoId", id);
		if(result.hasErrors()) {
			model.addAttribute("titulo","Agregar clase (Campos incorrectos)");
			return "/alumno/agregarclase";
		}
		
		Alumno alumno = serviceDb.findByIdAlumno(id);
		clase.setId(null);
		clase.addAlumno(alumno);
		serviceDb.saveClase(clase);
		return "redirect:/alumno/"+alumno.getId()+"/listaclases";
	}
	
	@GetMapping("/{idRes}/editarclase/{idMen}")
	public String editarClase(@PathVariable Integer idRes, @PathVariable Integer idMen,  Model model) {
		model.addAttribute("alumnoId", idRes);
		model.addAttribute("clase", serviceDb.findByIdClase(idMen)) ;
		model.addAttribute("titulo","Editar clase");
		return "/alumno/editarclase";
	}
	
	@PostMapping("/{idRes}/editarclase")
	public String editarClase(@PathVariable Integer idRes,@Valid Clase clase, BindingResult result ,Model model, SessionStatus status) {
		model.addAttribute("alumnoId", idRes);
		if(result.hasErrors()) {
			model.addAttribute("titulo","Editar clase (Campos incorrectos)");
			return "/alumno/editarclase";
		}
		
		model.addAttribute("alumnoId", idRes);
		serviceDb.saveClase(clase);
		status.setComplete();
		return "redirect:/alumno/"+idRes+"/listaclases";
	}
	
	@GetMapping("/{idRes}/eliminarclase/{idMen}")
	public String eliminarClase(@PathVariable Integer idRes , @PathVariable Integer idMen ,Model model) {
		Alumno alumno = serviceDb.findByIdAlumno(idRes);
		Clase clase = serviceDb.findByIdClase(idMen);
		clase.deleteAlumno(alumno);
		serviceDb.saveAlumno(alumno);
		return "redirect:/alumno/"+idRes+"/listaclases";
	}
}
