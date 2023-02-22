package com.jgr.commons.modelo.asig.exam.preg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class Examen.
 */
@Entity
@Table(name="examenes")
public class Examen {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The nombre. */
	@NotEmpty
	@Size(min = 4, max=30)
	private String nombre;
	
	/** The create at. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_at")
	private Date createAt;
	
	
	 //da error al consultar los atributos porque no puede deserializarlos,por eso el ignore properties
	 //https://www.udemy.com/course/microservicios-spring-cloud-y-angular-9/learn/lecture/17303344#questions/11766242
	/** The preguntas. 1 examen->n preguntas*/
	@JsonIgnoreProperties(value = {"examen"}, allowSetters = true)//para que no se embucle al listar preguntas
	@OneToMany(mappedBy = "examen", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pregunta> preguntas;
	
	
	 //da error al consultar los atributos porque no puede deserializarlos,por eso el ignore properties
	 //https://www.udemy.com/course/microservicios-spring-cloud-y-angular-9/learn/lecture/17303344#questions/11766242
	/** The asignatura. */
	@JsonIgnoreProperties(value= {"handler", "hibernateLazyInitializer"})
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private Asignatura asignatura;
	
	/** The respondido. */
	@Transient
	private boolean respondido;

	/**
	 * Instantiates a new examen.
	 */
	public Examen() {
		this.preguntas = new ArrayList<>();
	}

	/**
	 * Pre persist.
	 */
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
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
	 * Gets the creates the at.
	 *
	 * @return the creates the at
	 */
	public Date getCreateAt() {
		return createAt;
	}

	/**
	 * Sets the creates the at.
	 *
	 * @param createAt the new creates the at
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	/**
	 * Gets the preguntas.
	 *
	 * @return the preguntas
	 */
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	/**
	 * Sets the preguntas.
	 *
	 * @param preguntas the new preguntas
	 */
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas.clear();
		preguntas.forEach(this::addPregunta);
		
	}
	
	/**
	 * Adds the pregunta.
	 * agregamos tambien el examen en la pregunta,la relacion es bidireccional
	 *
	 * @param pregunta the pregunta
	 */
	public void addPregunta(Pregunta pregunta) {
		this.preguntas.add(pregunta);
		pregunta.setExamen(this);
	}

	/**
	 * Removes the pregunta.
	 *
	 * @param pregunta the pregunta
	 */
	public void removePregunta(Pregunta pregunta) {
		this.preguntas.remove(pregunta);
		pregunta.setExamen(null);
	}

	/**
	 * Gets the asignatura.
	 *
	 * @return the asignatura
	 */
	public Asignatura getAsignatura() {
		return asignatura;
	}

	/**
	 * Sets the asignatura.
	 *
	 * @param asignatura the new asignatura
	 */
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	/**
	 * Checks if is respondido.
	 *
	 * @return true, if is respondido
	 */
	public boolean isRespondido() {
		return respondido;
	}

	/**
	 * Sets the respondido.
	 *
	 * @param respondido the new respondido
	 */
	public void setRespondido(boolean respondido) {
		this.respondido = respondido;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Examen)) {
			return false;
		}

		Examen a = (Examen) obj;
		
		return this.id != null && this.id.equals(a.getId());
	}
	
	
}
