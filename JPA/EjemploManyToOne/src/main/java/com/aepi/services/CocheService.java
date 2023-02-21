package com.aepi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aepi.entities.Car;
import com.aepi.entities.Garage;
import com.aepi.repositories.ICocheRepository;
import com.aepi.repositories.IGarajeRepository;


@Service
public class CocheService {
	
	@Autowired
	private ICocheRepository cocheRepository;

	@Autowired
	private IGarajeRepository garajeRepository;

	public Object list() {
		return cocheRepository.findAll();
	}

	public Car save(Car car) {
		Garage garage = garajeRepository.getById(car.getGarage().getId());
		car.setGarage(garage);
		return cocheRepository.save(car);
	}

	public Car get(long id) {
		return cocheRepository.findById(id);
	}

	public Car update(Car car) {
		Car old = cocheRepository.findById(car.getId());
		old.setMarca(car.getMarca());
		old.setModelo(car.getModelo());
		old.setColor(car.getColor());
		old.setGarage(car.getGarage());
		
		car = cocheRepository.save(old);
		return car;
	}

	public void delete(long id) {
		cocheRepository.deleteById(id);
	}

	public List<Car> list(long garageId) {
		return cocheRepository.findByGarageId(garageId);
	}

}
