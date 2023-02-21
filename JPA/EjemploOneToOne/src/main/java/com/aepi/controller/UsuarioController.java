package com.aepi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.aepi.entity.Usuario;
import com.aepi.repository.DniRepository;
import com.aepi.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private DniRepository dniRepository;
	
	@GetMapping({"", "/"})
	public String index(Model modelo) {
		modelo.addAttribute("usuario",new Usuario());
		modelo.addAttribute("usuarios",usuarioRepository.findAll());
		modelo.addAttribute("dnis",dniRepository.findAll());
		return "index";
	}
	
	@PostMapping("/crearUsuario")
	public String crearUsuario(Model modelo, Usuario usuario) {
		usuarioRepository.save(usuario);
		
		modelo.addAttribute("usuario",new Usuario());
		modelo.addAttribute("usuarios",usuarioRepository.findAll());
		modelo.addAttribute("dnis",dniRepository.findAll());
		return "index";
	}
	
	@GetMapping("/editarUsuario/{id}")
	public String editarUsuarioForm(Model modelo, @PathVariable(name="id") Integer id) {
		Usuario usuarioParaEditar = usuarioRepository.findById(id).get();
		modelo.addAttribute("usuario",usuarioParaEditar);
		modelo.addAttribute("usuarios",usuarioRepository.findAll());//SELECT * FROM USUARIOS;
		modelo.addAttribute("roles",dniRepository.findAll());
		return "index";
	}
	
	@GetMapping("/eliminarUsuario/{id}")
	public String eliminarUsuario(Model modelo, @PathVariable(name="id") Integer id) {
		Usuario usuarioParaEliminar = usuarioRepository.findById(id).get();
		usuarioRepository.delete(usuarioParaEliminar);
		modelo.addAttribute("usuario",new Usuario());
		modelo.addAttribute("usuarios",usuarioRepository.findAll());//SELECT * FROM USUARIOS;
		modelo.addAttribute("usuarios",usuarioRepository.findAll());
		return "index";
	}
}
