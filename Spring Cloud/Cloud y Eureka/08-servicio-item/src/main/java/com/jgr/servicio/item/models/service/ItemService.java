package com.jgr.servicio.item.models.service;

import java.util.List;

import com.jgr.servicio.item.models.Item;

public interface ItemService {

	public List<Item> findAll();
	public Item findById(Long id, Integer cantidad);
	public Item findByIdError(Long id, Integer cantidad);
}
