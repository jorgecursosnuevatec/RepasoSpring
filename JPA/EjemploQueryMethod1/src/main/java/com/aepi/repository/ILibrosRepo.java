package com.aepi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aepi.model.Libro;

public interface ILibrosRepo extends JpaRepository<Libro, Integer> {
	
		
	@Query("select l from com.aepi.model.Libro l where l.id=:id")
	public List<Libro> getAllById(Integer id);
	
	@Query("select l from com.aepi.model.Libro l order by l.titulo asc")
	public List<Libro> getAllOrderAscByTitulo();
	
	@Query("select l from com.aepi.model.Libro l order by l.paginas desc")
	public List<Libro> getAllOrderDescByPaginas();
	
	@Query("select l from com.aepi.model.Libro l where l.paginas>:paginas")
	public List<Libro> getAllPaginasGreaterThan(int paginas);
	
	@Query("select l from com.aepi.model.Libro l where l.autor=:autor")
	public List<Libro> getAllAutorEqualTo(String autor);
	
	@Query("select l from com.aepi.model.Libro l where l.autor=:autor and l.paginas>:paginas")
	public List<Libro> getAllAutorEqualToAndPaginasGreaterThan(String autor, int paginas);

}
