package com.aepi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aepi.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {

	@Query(" select p from com.aepi.entity.Persona p where p.nombre=:nombre")
	public List<Persona> getPersonasPorNombre(String nombre);

	@Query(" select p from com.aepi.entity.Persona p")
	public List<Persona> getAllPersonas();
	
	// Parametros posicionales (?1,?2,?3........................................)
	// Al pasar los datos de esta manera no hacen falta los dos puntos
	@Query(" select p from com.aepi.entity.Persona p where p.id=?1 or p.salario>?2 ")
	public List<Persona> getDatosPosicionales(Integer id, Integer salario);

}
