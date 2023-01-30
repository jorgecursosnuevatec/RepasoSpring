package com.jgr.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jgr.entity.Genero;
import com.jgr.entity.Pelicula;
import com.jgr.errors.ErrorBBDDException;
import com.jgr.errors.IdNoEncontradoException;
import com.jgr.service.IGeneroService;
import com.jgr.service.IPeliculaService;
import com.jgr.util.PageRender;

@Controller

@RequestMapping(name = "/genero")
public class GeneroController {



	//@Autowired
	//private GeneroValidador validador;

	
	@Autowired
	private IGeneroService generoService;
	
	@Autowired
	private IPeliculaService peliculaService;

	//@PostConstruct
	public void cargaInicial() {
		String literales[] = { "Terror", "Ciencia-Ficcion", "Aventuras" ,"Comedia"};

		Genero gen;
		for (String tipo : literales) {
			gen = new Genero();
			gen.setTipoGenero(tipo.toUpperCase());
			generoService.save(gen);
			}
		

		java.util.Date dt = new java.util.Date();
		Pelicula peli;

		for (Genero gen2 : generoService.findAll()) {

			//3 pelis por genero,para ver el listado a ver como sale...

			for (int i=0;i<generoService.findAll().size();i++) {
			peli = new Pelicula();
			peli.setFechaEstrenoPelicula(dt);
			peli.setTituloPelicula("Titulo" + gen2.getTipoGenero());
			peli.setPortadaPelicula("");
			
			
			peli.setSinopsisPelicula("Sinopsis" + gen2.getTipoGenero()
			.concat(" Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod,\n"
					+"tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n"
					+"quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo,\n"
					+"consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse,\n"
					+"cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non,\n"
					+"proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
			);
			peli.setUrlPelicula("Url" + gen2.getTipoGenero());
			peli.setGenero(gen2);
			peliculaService.save(peli);
			}

		}

	}


	@GetMapping(value = {"/listar"})
	public String listaGenero(
			@RequestParam(name = "page", defaultValue = "0") int page,
			Model modelo) {
		
		Genero genero = new Genero();
		
		
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Genero> generos = generoService.findAll(pageRequest);
		PageRender<Genero> pageRender = new PageRender<Genero>("/listar", generos);

		modelo.addAttribute("genero", genero);
		modelo.addAttribute("listaGeneros", generos);
		modelo.addAttribute("tipoGenero", "");// para no enviar el objeto entero,solo necesito el campo con el nombre
		modelo.addAttribute("page", pageRender);
		return "genero/generoForm";
	}

	@PostMapping(value = { "/insertaGenero" })
	public String altaGeneroPost(@RequestParam(value = "tipoGenero", required = true) String tipoGenero,
			Model modelo,
			RedirectAttributes redirectAttrs,
			SessionStatus status) {

		

		//si no esta relleno, msg de error,ni accedo a bbdd
		if (tipoGenero == null || !(tipoGenero.trim().length() > 0)) {
			redirectAttrs.addFlashAttribute("mensaje1", "Revise los datos marcados en el Genero,son invalidos");
			redirectAttrs.addFlashAttribute("clase", "danger");	
			return "redirect:/listar";
		}

		Genero gen =new Genero();

		try {

			if(generoService.findFirstByTipoGenero(tipoGenero).isPresent()){
				redirectAttrs.addFlashAttribute("mensaje1", "El genero ".concat(tipoGenero).concat(" ya existe en el sistema"));
				redirectAttrs.addFlashAttribute("clase", "danger");		
				return "redirect:/listar";
			}


		} catch(DataAccessException e) {
			throw new ErrorBBDDException(e.getClass().toString().concat(e.getMessage()) );
		}

		gen.setTipoGenero(tipoGenero);
		generoService.save(gen);

		//al hacer redirect tengo que enviarlos asi a la pantalla inicial
		redirectAttrs.addFlashAttribute("mensaje2", "Alta correcta de ".concat(tipoGenero.toUpperCase()).concat(" como nueva categoria"));
		redirectAttrs.addFlashAttribute("clase", "success");
		return "redirect:/listar";

	}

	@PostMapping("/eliminarGenero/{id}")
	public String eliminaGenero(			
			@PathVariable int id,
			Model model,
			RedirectAttributes redirectAttrs
			) 
	{

		

		 String nombre="";
		//esto no deberia darse nunca,pero....
		try {
			
			Genero gen=generoService.findById(id).orElse(null);

			if(gen==null){
				throw new IdNoEncontradoException(String.valueOf(id));
			}
			
			nombre=gen.getTipoGenero();
			

		} catch(DataAccessException e) {
			throw new ErrorBBDDException(e.getClass().toString().concat(e.getMessage()) );
		}

		try {
			generoService.deleteById(id);			

		} catch(DataAccessException e) {
			throw new ErrorBBDDException(e.getClass().toString().concat(e.getMessage()) );
		}
		
		
		redirectAttrs.addFlashAttribute("mensaje2", "Borrado correcto de ".concat(nombre).concat(" como categoria"));
		redirectAttrs.addFlashAttribute("clase", "success");
		

		return "redirect:/listar";
	}




}
