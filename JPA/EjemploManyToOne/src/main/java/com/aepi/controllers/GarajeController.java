package com.aepi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aepi.entities.Garage;
import com.aepi.services.CocheService;
import com.aepi.services.GarajeService;

@Controller
@RequestMapping("/garaje")
public class GarajeController {

private static final String PATH = "/garaje";

	@Autowired
	private GarajeService garajeService;
	
	@Autowired
	private CocheService cocheService;
	
	@GetMapping({ "", "/list" })
	public String list(Model model) {
		model.addAttribute("garajes", garajeService.list());
		return PATH + "/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("coches", cocheService.list());
		return PATH + "/add";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute Garage garage, Model model) {
		garage = garajeService.save(garage);
		model.addAttribute("garage", garage);
		return "redirect:show/" + garage.getId();
	}
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") long id, Model model) {
		model.addAttribute("garage", garajeService.get(id));
		return PATH + "/show";
	}
	
	// TODO
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		model.addAttribute("cars", cocheService.list());
		model.addAttribute("garage", garajeService.get(id));
		return PATH + "/edit";
	}

	// TODO
	@PostMapping(value = "/update")
	public String update(@ModelAttribute Garage garage) {
		garage = garajeService.update(garage);
		return "redirect:" + PATH + "/show/" + garage.getId();
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		garajeService.delete(id);
		return "redirect:" + PATH + "/list";
	}
}
