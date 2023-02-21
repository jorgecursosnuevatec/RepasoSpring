
package com.jgr.curso.mongo.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Instantiates a new curso.
 * Como es MONGO ,se pone @Document en vez de @Table
 * Long no se puede utilizar en mongo,la clave ID la cambiamos a string
 */
@NoArgsConstructor

/**
 * Gets the creates the at.
 *
 * @return the creates the at
 */
@Getter

/**
 * Sets the creates the at.
 *
 * @param createAt the new creates the at
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString
@Document(collection="cursos")
public class Curso {

	
	/** The id.  en MONGO NO VALE LONG, se pone String*/
	
	//private Long id;
	@Id
	private String id;
	
	
	/** The nombre. */
	private String nombre;
	
	
	/** The create at. */
	 
	private Date createAt;
	 
	 
	@CreatedDate
		public void prePersist() {
			this.createAt = new Date();
		}
	

}
