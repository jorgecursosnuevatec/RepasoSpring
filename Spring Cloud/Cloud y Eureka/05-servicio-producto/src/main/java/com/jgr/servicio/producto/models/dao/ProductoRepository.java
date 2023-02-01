package com.jgr.servicio.producto.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.jgr.servicio.producto.models.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long>{

}
