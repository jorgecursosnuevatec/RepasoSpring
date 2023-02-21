package com.aepi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.aepi.model.Libro;
import com.aepi.repository.ILibrosRepo;

@Controller
public class LibrosController {

	@Autowired
	private ILibrosRepo repo;

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("libros", (List<Libro>) repo.findAll());
		model.addAttribute("libro", null);
		model.addAttribute("filtro", false);
		return "index";
	}

	@GetMapping("/form")
	public String nuevo(Model model) {
		model.addAttribute("libro", new Libro());
		return "index";
	}

	@PostMapping("/insert")
	public String insert(Libro libro, Model model) {
		repo.save(libro);
		return "redirect:/index";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {
		repo.deleteById(id);
		return "redirect:/index";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("libro", repo.getById(id));
		return "index";
	}

	@GetMapping("/filter")
	public String showfilter(Model model) {
		model.addAttribute("libros", null);
		model.addAttribute("libro", null);
		model.addAttribute("libroFiltro", new Libro());
		return "index";
	}

	@PostMapping("/filter")
	public String filterDerivadas(Libro libro, Integer filtro, boolean derivada, Model model) {
		List<Libro> libros = new ArrayList<Libro>();

		if (derivada) {
			switch (filtro) {
			case 1:
				Optional<Libro> libroBD = repo.findById(libro.getId());
				if (libroBD.isPresent())
					libros.add(libroBD.get());
				break;
			case 2:
				libros = repo.findAllByOrderByTituloAsc();
				break;
			case 3:
				libros = repo.findAllByOrderByPaginasDesc();
				break;
			case 4:
				libros = repo.findAllByPaginasGreaterThan(libro.getPaginas());
				break;
			case 5:
				libros = repo.findAllByAutor(libro.getAutor());
				break;
			case 6:
				
				libros = repo.findAllByAutorAndPaginasGreaterThan(libro.getAutor(), libro.getPaginas());
				break;
			default:
				libros = new ArrayList<Libro>();
				break;
			}
		} else {
			switch (filtro) {
			case 1:
				libros = repo.getAllById(libro.getId());
				break;
			case 2:
				libros = repo.getAllOrderAscByTitulo();
				break;
			case 3:
				libros = repo.getAllOrderDescByPaginas();
				break;
			case 4:
				libros = repo.getAllPaginasGreaterThan(libro.getPaginas());
				break;
			case 5:
				libros = repo.getAllAutorEqualTo(libro.getAutor());
				break;
			case 6:
				libros = repo.getAllAutorEqualToAndPaginasGreaterThan(libro.getAutor(), libro.getPaginas());
				break;
			default:
				libros = new ArrayList<Libro>();
				break;
			}
		}

		model.addAttribute("libros", libros);
		model.addAttribute("libro", null);
		model.addAttribute("filtro", false);
		return "index";
	}
}
