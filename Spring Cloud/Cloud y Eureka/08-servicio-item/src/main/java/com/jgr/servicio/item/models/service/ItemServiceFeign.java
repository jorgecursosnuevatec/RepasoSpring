package com.jgr.servicio.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jgr.servicio.item.cliente.feign.ProductoClienteRest;
import com.jgr.servicio.item.models.Item;
import com.jgr.servicio.item.models.Producto;

@Service("serviceFeign")
public class ItemServiceFeign implements ItemService {
	
	@Autowired
	private ProductoClienteRest clienteFeign;
	
	private final Logger logger = LoggerFactory.getLogger(ItemServiceFeign.class);

	@Override
	public List<Item> findAll() {
		return clienteFeign.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		logger.debug("En clienteFeing NO metodo error");
		return new Item(clienteFeign.detalle(id), cantidad);
	}
	@Override
	public Item findByIdError(Long id, Integer cantidad) {
		logger.debug("En clienteFeign metodo error");

		return new Item(clienteFeign.detalleError(id), cantidad);
	}

	@Override
	public Producto altaProducto(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto editar(Producto producto, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		
	}

}
