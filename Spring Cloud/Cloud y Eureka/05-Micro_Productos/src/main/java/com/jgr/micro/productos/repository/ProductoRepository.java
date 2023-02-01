package com.jgr.micro.productos.repository;

import org.springframework.data.repository.CrudRepository;

import com.jgr.micro.productos.entity.Producto;

/**
 * The Interface ProductoRepository.
 */
public interface ProductoRepository  extends CrudRepository<Producto, Long>{

}
