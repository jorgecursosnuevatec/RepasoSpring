package com.jgr.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jgr.entity.Rol;
import com.jgr.entity.Usuario;
import com.jgr.service.RolServiceImpl;
import com.jgr.service.UsuarioService;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioController {

	private UsuarioService usuarioServicio;

	@Autowired
	private RolServiceImpl rolService;

	public RegistroUsuarioController(UsuarioService usuarioServicio) {
		super();
		this.usuarioServicio = usuarioServicio;
	}

	@ModelAttribute("usuario")
	public Usuario retornarNuevoUsuarioRegistro() {
		return new Usuario();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro(Model model) {
		List<Rol> roles = rolService.findAll();
		model.addAttribute("roles", roles);
		return "login/registro";
	}

	// Crear una nueva cuenta de usuario desde registro.html
	// recibir el rol de usuario desde el visitante
	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") Usuario usuario,
			@RequestParam("rol") Long rolId,Model modelo) 
	{
		// tomar el rol elegido por el visitante
		Optional<Rol> rol = rolService.findById(rolId);
		if (rol.isPresent()) {
			usuario.addRol(rol.get());
			usuario = usuarioServicio.guardar(usuario);
		}
		modelo.addAttribute("usuario",usuario);
		// no se ha guardado porque ya existe un usuario con el mismo email
		if (usuario == null)
			return "redirect:/registro?fracaso";
		else	
			return "redirect:/registro?exito";
	}
}
