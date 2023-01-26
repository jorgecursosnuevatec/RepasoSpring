package com.jgr.repaso.thymeleaf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@Entity
@Table(name="personas")
public class Persona implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersona;
	
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apellido;
    
    @Email
    @NotEmpty
    private String email;
    //@NotEmpty
    private String telefono;
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 5519104107496266200L;
}
