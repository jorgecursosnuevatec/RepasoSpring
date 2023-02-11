package com.jgr.micro.alumno.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * The Class Alumno.
 */
@Entity
@Table(name="alumnos")
@Getter
@Setter
@ToString
public class Alumno {
	
	/** The id alumno. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAlumno;
	
	/** The nombre. */
	private String nombre;
	
	/** The apellido. */
	private String apellidos;
	
	/** The email. */
	private String email;
	
	/** The create at. */
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	/** The foto. */
	@Lob
	private byte[] foto;
	
	
	//se le asigna un valor automaticamente antes de que se inserte en BBDD
	@PrePersist
	public void prePersist() {
		this.createAt= new Date();
	}
	

}
