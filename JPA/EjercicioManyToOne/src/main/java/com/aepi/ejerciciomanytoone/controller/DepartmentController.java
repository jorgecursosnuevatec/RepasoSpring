package com.aepi.ejerciciomanytoone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aepi.ejerciciomanytoone.entity.Department;
import com.aepi.ejerciciomanytoone.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	private static final String PATH = "/department";

	@Autowired
	private DepartmentService departmentService;

	@GetMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("departments", departmentService.list());
		return PATH + "/list";
	}

	@GetMapping(value = "/show/{id}")
	public String show(@PathVariable("id") long id, Model model) {
		model.addAttribute("department", departmentService.get(id));
		return PATH + "/show";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		return PATH + "/create";
	}

	@PostMapping(value = "save")
	public String save(@ModelAttribute Department department, Model model) {
		department = departmentService.save(department);
		model.addAttribute("department", department);
		return "redirect:" + PATH + "/show/" + department.getId();
	}

	@PutMapping(value = "/update")
	public String update(@ModelAttribute Department department, Model model) {
		department = departmentService.update(department);
		model.addAttribute("department", department);
		return "redirect:" + PATH + "/show/" + department.getId();
	}

	@DeleteMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") long id, Model model) {
		model.addAttribute("department", departmentService.delete());
		return PATH;
	}
}
