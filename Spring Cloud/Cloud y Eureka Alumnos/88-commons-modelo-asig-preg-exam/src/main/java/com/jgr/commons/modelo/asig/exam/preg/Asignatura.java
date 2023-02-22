package com.jgr.commons.modelo.asig.exam.preg;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class Asignatura.
 */
@Entity
@Table(name="asignaturas")
public class Asignatura {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The nombre. */
	private String nombre;
	
	
	 //da error al consultar los atributos porque no puede deserializarlos,por eso el ignore properties
	 //https://www.udemy.com/course/microservicios-spring-cloud-y-angular-9/learn/lecture/17303344#questions/11766242
	/** The padre. */
	@JsonIgnoreProperties(value= {"hijos", "handler", "hibernateLazyInitializer"})
	@ManyToOne(fetch = FetchType.LAZY)
	private Asignatura padre;

	
	 //da error al consultar los atributos porque no puede deserializarlos,por eso el ignore properties
	 //https://www.udemy.com/course/microservicios-spring-cloud-y-angular-9/learn/lecture/17303344#questions/11766242
	/** The hijos. */
	@JsonIgnoreProperties(value = {"padre", "handler", "hibernateLazyInitializer"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "padre", cascade = CascadeType.ALL)
	private List<Asignatura> hijos;

	/**
	 * Instantiates a new asignatura.
	 */
	public Asignatura() {
		this.hijos = new ArrayList<>();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the padre.
	 *
	 * @return the padre
	 */
	public Asignatura getPadre() {
		return padre;
	}

	/**
	 * Sets the padre.
	 *
	 * @param padre the new padre
	 */
	public void setPadre(Asignatura padre) {
		this.padre = padre;
	}

	/**
	 * Gets the hijos.
	 *
	 * @return the hijos
	 */
	public List<Asignatura> getHijos() {
		return hijos;
	}

	/**
	 * Sets the hijos.
	 *
	 * @param hijos the new hijos
	 */
	public void setHijos(List<Asignatura> hijos) {
		this.hijos = hijos;
	}
	
	

}
