package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	@Query(" select p from com.app.entity.Producto p where p.precio>:precioMenor and p.precio<:precioMayor")
	public List<Producto> getPrecioRango(Integer precioMenor, Integer precioMayor);

	@Query(" select p from com.app.entity.Producto p")
	public List<Producto> getAllProductos();

	@Query(" select p from com.app.entity.Producto p where p.activo=:activo")
	public List<Producto> getAllProductosActivos(Boolean activo);
	
	@Query(" select p from com.app.entity.Producto p where p.vendedor=:vendedor")
	public List<Producto> getProductosVendedor(String vendedor);

	@Query(" select p from com.app.entity.Producto p order by p.id desc ")
	public List<Producto> getDatosOrdenados();
	
	// Parametros posicionales (?1,?2,?3........................................)
	// Al pasar los datos de esta manera no hacen falta los dos puntos
	@Query(" select p from com.app.entity.Producto p where p.id=?1 or p.precio<?2 ")
	public List<Producto> getDatosPosicionales(Integer id, Integer precio);

}
