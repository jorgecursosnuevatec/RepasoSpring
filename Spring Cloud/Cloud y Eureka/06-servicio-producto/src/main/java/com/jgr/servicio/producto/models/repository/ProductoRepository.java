package com.jgr.servicio.producto.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.jgr.commons.modelo.datos.models.entity.Producto;



/**
 * The Interface ProductoRepository.
 */
public interface ProductoRepository extends CrudRepository<Producto, Long>{

}
