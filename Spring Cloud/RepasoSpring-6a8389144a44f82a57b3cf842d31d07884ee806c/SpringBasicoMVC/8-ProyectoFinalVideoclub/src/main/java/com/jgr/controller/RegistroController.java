package com.jgr.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jgr.entity.Usuario;
import com.jgr.service.UsuarioService;

@Controller
public class RegistroController {

	@Autowired
	private UsuarioService servicio;

	@GetMapping("/login")
	public String iniciarSesion() {
		return "login/login";
	}

	@GetMapping("/")
	public String verPaginaDeInicio(HttpServletRequest request, Model modelo) {
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		// recuperar el usuario en sesion
		Principal principal = request.getUserPrincipal();
		Usuario usuario = servicio.findByEmail(principal.getName());

		// si el usuario actual tiene el rol_admin mostrar todos los usuarios
		if (usuario!=null && usuario.getRoles().stream().filter(o -> o.getNombre().equals("ROLE_ADMIN")).findFirst().isPresent()) {
			
			usuarios = servicio.listarUsuarios();
		} else {
			usuarios.add(usuario);
		}

		modelo.addAttribute("usuarios", usuarios);

		return "login/index";
	}

	@Secured("ROLE_USER")
	@GetMapping("/perfil")
	public String pefil(HttpServletRequest request, Model model) {
		// recuperar el usuario en sesion
		Principal principal = request.getUserPrincipal();

		Usuario usuario = servicio.findByEmail(principal.getName());

		model.addAttribute("usuario", usuario);

		return "login/perfil";
	}

	// Panel de administracion
	//

	@Secured("ROLE_ADMIN")
	@GetMapping("/admin")
	public String admin(Model model) {

		model.addAttribute("usuarios", servicio.listarUsuarios());
		return "login/admin";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/form/usuario/{id}")
	public String formUsuario(@PathVariable("id") Long id, Model model) 
	{
		Optional<Usuario> usuario = servicio.findById(id);

		if (usuario.isPresent())
			model.addAttribute("usuario", usuario.get());
		else
			model.addAttribute("usuarios", servicio.listarUsuarios());

		return "login/admin";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/usuario")
	public String editarUsuario(Usuario usuario, Model model) 
	{
		servicio.update(usuario);

//		model.addAttribute("usuario", null);
//		model.addAttribute("usuarios", servicio.listarUsuarios());
		return "redirect:/admin";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/delete/usuario/{id}")
	public String deleteUsuario(@PathVariable("id") Long id, Model model) 
	{
		servicio.deleteById(id);
		return "redirect:/admin";
	}

}
