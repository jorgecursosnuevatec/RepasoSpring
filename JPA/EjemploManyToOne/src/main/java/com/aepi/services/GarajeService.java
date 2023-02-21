package com.aepi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aepi.entities.Garage;
import com.aepi.repositories.IGarajeRepository;

@Service
public class GarajeService {

	@Autowired
	private IGarajeRepository garajeRepository;

	public List<Garage> list() {
		return garajeRepository.findAll();
	}

	public void delete(long id) {
		garajeRepository.deleteById(id);
	}

	public Garage update(Garage garage) {
		Garage old = garajeRepository.findById(garage.getId());
		old.setName(garage.getName());
		old.setDireccion(garage.getDireccion());
		old.setCars(garage.getCars());
		
		garage = garajeRepository.save(old);
		return garage;
	}

	public Garage save(Garage garage) {
		return garajeRepository.save(garage);
	}

	public Garage get(long id) {
		return garajeRepository.getById(id);
	}
}
