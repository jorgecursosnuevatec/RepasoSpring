package com.jgr.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.jgr.thymeleaf.model.Carrito;

@Controller
@SessionAttributes("carrito")
public class CestaController {

	@GetMapping("/cesta")
	public String cesta(@SessionAttribute("carrito") Carrito carrito, Model model) {
		if (carrito != null) {
			model.addAttribute("titulo", "Cesta de la compra");
			model.addAttribute("carrito", carrito);
			model.addAttribute("cesta", true);
			return "cesta";
		} else {
			return "redirect:/form";
		}
	}
	@GetMapping("/pagar")
	public String cesta(@SessionAttribute("carrito") Carrito carrito, Model model,
			SessionStatus status) {
		if (carrito != null) {
			model.addAttribute("titulo", "Pagar la compra");
			model.addAttribute("carrito", carrito);
			model.addAttribute("pagar", true);
			status.setComplete();  // finaliza la sesion
			return "cesta";
		} else {
			return "redirect:/form";
		}
	}
	

}
