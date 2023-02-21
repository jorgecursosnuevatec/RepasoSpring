package com.aepi.ejerciciomanytoone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aepi.ejerciciomanytoone.entity.Employee;
import com.aepi.ejerciciomanytoone.service.DepartmentService;
import com.aepi.ejerciciomanytoone.service.EmployeeService;

@Controller
@RequestMapping({ "", "/employee" })
public class EmployeeController {
	
	private static final String PATH = "/employee";
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@GetMapping({ "", "/list" })
	public String list(Model model) {
		model.addAttribute("employees", employeeService.list());
		return PATH + "/list";
	}

	@GetMapping("/list/{departmentId}")
	public String list(@PathVariable("departmentId") long departmentId, Model model) {
		model.addAttribute("employees", employeeService.list(departmentId));
		return PATH + "/list";
	}

	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("departments", departmentService.list());
		return PATH + "/add";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute Employee employee, Model model) {
		employee = employeeService.save(employee);
		model.addAttribute("employee", employee);
		return "redirect:show/" + employee.getId();
	}

	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") long id, Model model) {
		model.addAttribute("employee", employeeService.get(id));
		return PATH + "/show";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		model.addAttribute("employee", employeeService.get(id));
		return PATH + "/edit";
	}

	@PostMapping(value = "/update")
	public String update(@ModelAttribute Employee employee) {
		employee = employeeService.update(employee);
		return "redirect:" + PATH + "/show/" + employee.getId();
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		employeeService.delete(id);
		return "redirect:" + PATH + "/list";
	}
}
