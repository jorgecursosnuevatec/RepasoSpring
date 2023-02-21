package com.aepi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aepi.entities.Car;
import com.aepi.services.CocheService;
import com.aepi.services.GarajeService;

@Controller
@RequestMapping({"", "/coche"})
public class CocheController {
	
	private static final String PATH = "/coche";
	
	@Autowired
	private CocheService cocheService;

	@Autowired
	private GarajeService garajeService;
	
	@GetMapping({ "", "/list" })
	public String list(Model model) {
		model.addAttribute("coches", cocheService.list());
		return PATH + "/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("garajes", garajeService.list());
		return PATH + "/add";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute Car car, Model model) {
		car = cocheService.save(car);
		model.addAttribute("coche", car);
		return "redirect:show/" + car.getId();
	}
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") long id, Model model) {
		model.addAttribute("car", cocheService.get(id));
		return PATH + "/show";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		model.addAttribute("car", cocheService.get(id));
		model.addAttribute("garajes", garajeService.list());
		return PATH + "/edit";
	}

	@PostMapping(value = "/update")
	public String update(@ModelAttribute Car car) {
		car = cocheService.update(car);
		return "redirect:" + PATH + "/show/" + car.getId();
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		cocheService.delete(id);
		return "redirect:" + PATH + "/list";
	}

}
