package com.jgr.micro.respuestas.sql.models.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jgr.commons.modelo.alumnos.Alumno;
import com.jgr.commons.modelo.asig.exam.preg.Pregunta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class Respuesta.
 */
@Entity
@Table(name="respuestas")
public class Respuesta {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The texto. */
	private String texto;
	
	
	 //da error al consultar los atributos porque no puede deserializarlos,por eso el ignore properties
	 //https://www.udemy.com/course/microservicios-spring-cloud-y-angular-9/learn/lecture/17303344#questions/11766242
	/** The alumno. */
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	private Alumno alumno;
	

	 //da error al consultar los atributos porque no puede deserializarlos,por eso el ignore properties
	 //https://www.udemy.com/course/microservicios-spring-cloud-y-angular-9/learn/lecture/17303344#questions/11766242
	 
	/** The pregunta. */
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY)
	private Pregunta pregunta;

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
	 * Gets the texto.
	 *
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * Sets the texto.
	 *
	 * @param texto the new texto
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * Gets the alumno.
	 *
	 * @return the alumno
	 */
	public Alumno getAlumno() {
		return alumno;
	}

	/**
	 * Sets the alumno.
	 *
	 * @param alumno the new alumno
	 */
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	/**
	 * Gets the pregunta.
	 *
	 * @return the pregunta
	 */
	public Pregunta getPregunta() {
		return pregunta;
	}

	/**
	 * Sets the pregunta.
	 *
	 * @param pregunta the new pregunta
	 */
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	
	
}
