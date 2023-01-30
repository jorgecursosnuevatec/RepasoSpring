package com.jgr.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jgr.entity.Genero;
import com.jgr.entity.Pelicula;
import com.jgr.errors.ErrorBBDDException;
import com.jgr.errors.IdNoEncontradoException;
import com.jgr.service.IGeneroService;
import com.jgr.service.IPeliculaService;
import com.jgr.util.PageRender;

@Controller
@RequestMapping(name = "/pelicula")
@SessionAttributes("pelicula")
public class PeliculaController {

	@Autowired
	private IPeliculaService peliculaService;

	// para sacar la lista de generos a asociar a la pelicula
	@Autowired
	private IGeneroService generoService;

	// lista de generos
	@ModelAttribute("listaGeneros")
	public HashMap<Long, String> listaGeneros() {

		HashMap<Long, String> generos = new HashMap<Long, String>();

		for (Genero gen : generoService.findAll()) {
			generos.put(gen.getId(), gen.getTipoGenero());

		}

		return generos;
	}

	@GetMapping(value = { "/listarPeliculas" })
	public String listaPeliculas(@RequestParam(name = "page", defaultValue = "0") int page, Model modelo) {
		Pelicula peli = new Pelicula();
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Pelicula> peliculas = peliculaService.findAllPeliculas(pageRequest);
		PageRender<Pelicula> pageRender = new PageRender<Pelicula>("/listarPeliculas", peliculas);
		modelo.addAttribute("pelicula", peli);
		modelo.addAttribute("listaPeliculas", peliculas);
		modelo.addAttribute("page", pageRender);
		return "pelicula/listaPeliculas";
	}

	@PostMapping(value = { "/insertaPelicula" })
	public String Alta(@Valid Pelicula peli, BindingResult result, Model modelo, @RequestParam String genero,
			@RequestParam(name = "file") MultipartFile foto, SessionStatus status, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {

			modelo.addAttribute("mensaje1", "Revise los datos marcados,son incorrectos");
			modelo.addAttribute("clase", "danger");
			return "pelicula/formAltaPelicula";
		}

		// si no ha seleccionado el genero  envio msg
		try {
			Integer.valueOf(genero);
		} catch (NumberFormatException e) {
			modelo.addAttribute("mensaje1", "Debe seleccionar un genero de la lista");
			modelo.addAttribute("clase", "danger");
			// para forzar error y sacar msg que se asocia al messages.properties
			// result.rejectValue("genero", "error.genero", "Selecciona un genero de la
			// lista");
			return "pelicula/formAltaPelicula";

		}

		if (foto.isEmpty()) {
			
			modelo.addAttribute("mensaje1", "Debe incluir la portada de la pelicula");
			modelo.addAttribute("clase", "danger");
			return "pelicula/formAltaPelicula";

		}

		try {
			
			if (peliculaService.findByTituloPeliculaIgnoreCase(peli.getTituloPelicula()).isPresent()) {
				
				modelo.addAttribute("mensaje1",
						"La pelicula ".concat(peli.getTituloPelicula()).concat(" ya existe en el sistema"));
				modelo.addAttribute("clase", "danger");
				return "pelicula/formAltaPelicula";
			}

		} catch (DataAccessException e) {
			throw new ErrorBBDDException(e.getClass().toString().concat(e.getMessage()));
		}

		if (!foto.isEmpty()) {
			
			Path dirRecursos = Paths.get("src/main/resources/static/images");
			String rootPath = dirRecursos.toFile().getAbsolutePath();

			try {
			
				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "/" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				modelo.addAttribute("info", "Has subido '" + foto.getOriginalFilename() + "'");

				// CONVIERTO LA FOTO A BASE64
				peli.setPortadaPelicula(Base64.getEncoder().encodeToString(bytes));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		peliculaService.save(peli);

		status.setComplete();
		modelo.addAttribute("pelicula", peli);
		modelo.addAttribute("listaGeneros", listaGeneros());
		redirectAttrs.addFlashAttribute("mensaje2", "Alta correcta de la pelicula ".concat(peli.getTituloPelicula()));
		redirectAttrs.addFlashAttribute("clase", "success");
		return "redirect:listarPeliculas";
	}

	@GetMapping(value = { "/prevAltaPelicula" })
	public String prevAlta(Model modelo, Pelicula peli) {

		modelo.addAttribute("pelicula", peli);
		modelo.addAttribute("listaGeneros", listaGeneros());
		return "pelicula/formAltaPelicula";
	}

	@GetMapping(value = { "/prevModif/{id}" })
	public String prevModifPelicula(@PathVariable int id, Model modelo, RedirectAttributes redirectAttrs) {

		// esto no deberia darse nunca,pero....
		try {

			Pelicula peli = peliculaService.findById(id).orElse(null);

			if (peli == null) {
				throw new IdNoEncontradoException(String.valueOf(id));
			}

			modelo.addAttribute("pelicula", peli);

		} catch (DataAccessException e) {
			throw new ErrorBBDDException(e.getClass().toString().concat(e.getMessage()));
		}

		modelo.addAttribute("listaGeneros", listaGeneros());
		return "pelicula/formModificarPelicula";
	}

	@PostMapping("/eliminarPeli/{id}")
	public String eliminaPelicula(@PathVariable int id, Model model, RedirectAttributes redirectAttrs,
			SessionStatus status) {

		

		String nombre = "";
		// esto no deberia darse nunca,pero....
		try {

			Pelicula peli = peliculaService.findById(id).orElse(null);

			if (peli == null) {
				throw new IdNoEncontradoException(String.valueOf(id));
			}

			nombre = peli.getTituloPelicula();

		} catch (DataAccessException e) {
			throw new ErrorBBDDException(e.getClass().toString().concat(e.getMessage()));
		}

		try {
			peliculaService.deletePeliculaById(id);

		} catch (DataAccessException e) {
			throw new ErrorBBDDException(e.getClass().toString().concat(e.getMessage()));
		}

		redirectAttrs.addFlashAttribute("mensaje2", "Borrado correcto de la pelicula ".concat(nombre));
		redirectAttrs.addFlashAttribute("clase", "success");
		status.setComplete();
		return "redirect:/listarPeliculas";
	}

	@PostMapping(value = { "/modificaPelicula" })
	public String modifPelicula(@Valid Pelicula peli, BindingResult result, Model modelo, @RequestParam String genero,
			@RequestParam(name = "file") MultipartFile foto, SessionStatus status, RedirectAttributes redirectAttrs) {

		

		if (result.hasErrors()) {
			
			modelo.addAttribute("mensaje1", "Revise los datos marcados,son incorrectos");
			modelo.addAttribute("clase", "danger");
			modelo.addAttribute("pelicula", peli);
			return "pelicula/formModificarPelicula";
		}

		// si no ha seleccionado el genero en vez de cascar envio msg
		try {
			Integer.valueOf(genero);
		} catch (NumberFormatException e) {
			modelo.addAttribute("mensaje1", "Debe seleccionar un genero de la lista");
			modelo.addAttribute("clase", "danger");
			result.rejectValue("genero", "error.user", "Selecciona un genero de la lista");
			modelo.addAttribute("pelicula", peli);
			return "pelicula/formModificarPelicula";

		}
		Pelicula peliant = new Pelicula();
		// verificar que existe
		try {
			
			peliant = peliculaService.findById(peli.getId()).orElse(null);

			if (peliant == null) {
			
				modelo.addAttribute("mensaje1",
						"La pelicula ".concat(peli.getTituloPelicula()).concat(" no existe en el sistema"));
				modelo.addAttribute("clase", "danger");
				modelo.addAttribute("pelicula", peli);
				return "pelicula/formModificarPelicula";
			}

		} catch (DataAccessException e) {
			throw new ErrorBBDDException(e.getClass().toString().concat(e.getMessage()));
		}

		// si no ha seleccionado portada dejamos la que tiene
		if (foto.isEmpty()) {
			peli.setPortadaPelicula(peliant.getPortadaPelicula());

		} else {

			try {
				
				Path dirRecursos = Paths.get("src/main/resources/static/images");
				String rootPath = dirRecursos.toFile().getAbsolutePath();
				
				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "/" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				modelo.addAttribute("info", "Has subido '" + foto.getOriginalFilename() + "'");

				// CONVIERTO LA FOTO A BASE64
				peli.setPortadaPelicula(Base64.getEncoder().encodeToString(bytes));

			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		peli.setId(peliant.getId());
		peliculaService.save(peli);

		redirectAttrs.addFlashAttribute("mensaje2",
				"Modificacion correcta de la pelicula ".concat(peli.getTituloPelicula()));
		redirectAttrs.addFlashAttribute("clase", "success");
		status.setComplete();
		return "redirect:listarPeliculas";
	}

}
