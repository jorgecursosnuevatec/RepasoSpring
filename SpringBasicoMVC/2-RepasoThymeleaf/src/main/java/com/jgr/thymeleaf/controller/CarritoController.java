package com.jgr.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jgr.thymeleaf.model.Carrito;

@Controller
@SessionAttributes("carrito")
public class CarritoController {
	
	//lo recupera desde el properties
	@Value("${jorge.mensaje}")
	private String msgProperties;
	
	@GetMapping({"/form","/"})
	public String form(Model model) 
	
	{
		model.addAttribute("msgProperties", msgProperties);
		model.addAttribute("titulo", "Carrito de la compra");
		return "form";
	}

	@PostMapping("/addProducto")
	public String resultado(@ModelAttribute Carrito carrito, String nuevoProducto, Model model) 
	{
		model.addAttribute("titulo", "Carrito de la compra");
		if (carrito == null || nuevoProducto.trim().length() == 0) 
		{
			System.out.println("vacio");
			Carrito carritoNuevo = new Carrito(carrito);
			model.addAttribute("carrito", carritoNuevo);
			model.addAttribute("nuevoProducto", "");
		} 
		else 
		{
			System.out.println("lleno");
			// añadir el nuevo producto a la lista
			carrito.addProducto(nuevoProducto);
			model.addAttribute("carrito", carrito);
		}

		return "form";
	}

	@GetMapping("/acesta")
	public String cesta(Model model) {

	
		return "redirect:/cesta";
	}

	@ModelAttribute("carrito")
	public Carrito carrito() {
		return new Carrito();
	}
}
