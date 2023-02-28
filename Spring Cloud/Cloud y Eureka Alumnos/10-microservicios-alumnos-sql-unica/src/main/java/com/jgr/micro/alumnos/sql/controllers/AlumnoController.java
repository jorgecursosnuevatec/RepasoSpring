package com.jgr.micro.alumnos.sql.controllers;

import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;


import org.slf4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jgr.commons.controller.CommonController;
import com.jgr.commons.modelo.alumnos.Alumno;
import com.jgr.micro.alumnos.sql.services.AlumnoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;



// TODO: Auto-generated Javadoc
/**
 * The Class AlumnoController.
 * 
 * ACTUALIZAR PUERTO CON EL QUE SE EJECUTE PARA VER DOCUMENTACION
 * 
 * Documentado como JSON CONTROLLER->localhost:8080/v3/api-docs
 * Documentado como HTML CONTROLLER->http://localhost:8080/swagger-ui/index.html
 */
@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService>{
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(AlumnoController.class);
	
	/**
	 * Ver foto.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@Operation(summary = "Ver foto alumno por id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Alumno encontrado", 
	    content = { @Content(mediaType = "image/jpeg")}),
	  @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
	    content = @Content), 
	  @ApiResponse(responseCode = "404", description = "Alumno no existe/sin foto", 
	    content = @Content) })
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id){
	
		Optional<Alumno> o = service.findById(id);
		
		if(o.isEmpty() || o.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}
		
		Resource imagen = new ByteArrayResource(o.get().getFoto());
		
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(imagen);
	}
	
	/**
	 * Editar.
	 *
	 * @param alumno the alumno
	 * @param result the result
	 * @param id the id
	 * @return the response entity
	 */
	
	 @Operation(summary = "Actualiza alumno", 
			 description = "actualizacion de un alumno", operationId = "@PutMapping(\"/{id}\")")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "ok, alumno actualizado",
	                    content = @Content(schema = @Schema(implementation = Alumno.class))) ,
	            @ApiResponse(responseCode = "401", description = "Unauthorized"),
	            @ApiResponse(responseCode = "403", description = "Forbidden"),
	            @ApiResponse(responseCode = "404", description = "Not found")})
	 
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, BindingResult result, @PathVariable Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Alumno> o = service.findById(id);
		
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoDb = o.get();
		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellido(alumno.getApellido());
		alumnoDb.setEmail(alumno.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
	}
	
	/**
	 * Filtrar.
	 *
	 * @param term the term
	 * @return the response entity
	 */
	 @Operation(summary = "Busqueda de alumnos por nombre o apellido", 
			 description = "busqueda de alumno", operationId = "@GetMapping(\"/filtrar/{term}\")")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "ok, alumno(s) encontrado(s)",
	                    content = @Content(mediaType ="application/json",
	                    		array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))) ,
	            @ApiResponse(responseCode = "401", description = "Unauthorized"),
	            @ApiResponse(responseCode = "403", description = "Forbidden"),
	            @ApiResponse(responseCode = "404", description = "Not found")})
	 
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@Parameter(description = "nombre o apellido a buscar") @PathVariable String term){
		return ResponseEntity.ok(service.findByNombreOrApellidoIgnoringCase(term));
	}

	 /**
	  * cache.
	  *PRUEBA CON CACHE,SE DA DE ALTA EL CACHE
	  * @param term the term
	  * @return the response entity
	  * Le añado la notacion @Cacheable y el nombre que le he dado al atributo en CacheConfiguration
	  * guardaCache
	  */
	 @Operation(summary = "Busqueda de alumnos por nombre o apellido USANDO CACHE", 
			 description = "busqueda de alumno USANDO CACHE ", operationId = "@GetMapping(\"/cacheAlta/{term}\")")
	 @ApiResponses(value = {
			 @ApiResponse(responseCode = "200", description = "ok, alumno(s) encontrado(s)",
					 content = @Content(mediaType ="application/json",
					 array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))) ,
			 @ApiResponse(responseCode = "401", description = "Unauthorized"),
			 @ApiResponse(responseCode = "403", description = "Forbidden"),
			 @ApiResponse(responseCode = "404", description = "Not found")})

	 @GetMapping("/cacheAlta/{term}")
	 @Cacheable("guardaCache")
		 public ResponseEntity<?> altaCache(@Parameter(description = "nombre o apellido a buscar")
		 @PathVariable String term){
		 log.error("*********************Entra en ALTA cache********");
		 return ResponseEntity.ok(service.findByNombreOrApellidoIgnoringCase(term));
	 }
	 
	 /**
	  * cache.
	  *PRUEBA CON CACHE,SE DA DE BAJA EL CACHE
	  * @param term the term
	  * @return the response entity
	  * Le añado la notacion @Cacheable y el nombre que le he dado al atributo en CacheConfiguration
	  * guardaCache
	  */
	 @Operation(summary = "Borrado del cache", 
			 description = "borrado del CACHE ", operationId = "@GetMapping(\"/cacheBaja/{term}\")")
	 @ApiResponses(value = {
			 @ApiResponse(responseCode = "200", description = "ok, alumno(s) encontrado(s)",
					 content = @Content(mediaType ="application/json",
					 array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))) ,
			 @ApiResponse(responseCode = "401", description = "Unauthorized"),
			 @ApiResponse(responseCode = "403", description = "Forbidden"),
			 @ApiResponse(responseCode = "404", description = "Not found")})

	 @GetMapping("/cacheBaja/{id}")
	 @Cacheable("guardaCache")
		 public void bajaCache(@Parameter(description = "nombre o apellido a buscar")
		 @PathVariable Long id){
		 log.error("*********************Entra en BORRAR cache********");
		 
		 limpiaCache(id);
		
	 }
	 
	 /**
 	 * Limpia cache.
 	 */
 	@CacheEvict("guardaCache")
	 public void limpiaCache(Long id) {
 		
		 log.error("*********************Entra en limpiaCache()********");
		 
	 }


	/**
	 * Crear con foto.
	 *
	 * @param alumno the alumno
	 * @param result the result
	 * @param archivo the archivo
	 * @return the response entity
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	 @Operation(summary = "Alta de alumno con foto", 
			 description = "alta de alumno con foto", operationId = " @PostMapping(\"/crear-con-foto\")")
	 @ApiResponses(value = {
			 @ApiResponse(responseCode = "200", description = "ok, alta correcta",
					 content = @Content(mediaType ="application/json",
					 array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))) ,
			 @ApiResponse(responseCode = "401", description = "Unauthorized"),
			 @ApiResponse(responseCode = "403", description = "Forbidden"),
			 @ApiResponse(responseCode = "400", description = "Bad Request, faltan datos"),
			 @ApiResponse(responseCode = "404", description = "Not found")})	
	 @PostMapping("/crear-con-foto")
	 
	public ResponseEntity<?> crearConFoto(
			@Parameter(description = "alumno a dar de alta") @Valid Alumno alumno, BindingResult result,
			@Parameter(description = "foto del alumno")
			@RequestParam MultipartFile archivo) throws IOException {
		if(!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		return super.crear(alumno, result);
	}
	
	/**
	 * Editar con foto.
	 *
	 * @param alumno the alumno
	 * @param result the result
	 * @param id the id
	 * @param archivo the archivo
	 * @return the response entity
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	 @Operation(summary = "Modificacion de alumno con foto", 
			 description = "modificacion de alumno con foto", operationId = " @PutMapping(\"/editar-con-foto/{id}\")")
	 @ApiResponses(value = {
			 @ApiResponse(responseCode = "200", description = "ok, modificacion correcta",
					 content = @Content(mediaType ="application/json",
					 array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))) ,
			 @ApiResponse(responseCode = "400", description = "Bad Request, faltan datos"),
			 @ApiResponse(responseCode = "401", description = "Unauthorized"),
			 @ApiResponse(responseCode = "403", description = "Forbidden"),
			 @ApiResponse(responseCode = "404", description = "Not found")})	
	 
	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editarConFoto(
			@Parameter(description = "alumno a modificar") @Valid Alumno alumno, BindingResult result, 
			@Parameter(description = "id del alumno") @PathVariable Long id,
			@Parameter(description = "foto del alumno") @RequestParam MultipartFile archivo) throws IOException{
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Alumno> o = service.findById(id);
		
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoDb = o.get();
		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellido(alumno.getApellido());
		alumnoDb.setEmail(alumno.getEmail());
		
		if(!archivo.isEmpty()) {
			alumnoDb.setFoto(archivo.getBytes());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
	}

}
