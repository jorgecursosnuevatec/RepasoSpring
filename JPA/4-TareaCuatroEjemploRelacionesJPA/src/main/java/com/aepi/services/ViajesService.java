package com.aepi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aepi.model.Viajero;
import com.aepi.model.Asiento;
import com.aepi.model.Tren;
import com.aepi.model.Vagon;
import com.aepi.repository.IViajeroRepo;
import com.aepi.repository.IAsientoRepo;
import com.aepi.repository.ITrenRepo;
import com.aepi.repository.IVagonRepo;

@Service
public class ViajesService {

	@Autowired
	private IViajeroRepo viajeroRepo;

	@Autowired
	private ITrenRepo trenRepo;

	@Autowired
	private IVagonRepo vagonRepo;

	@Autowired
	private IAsientoRepo asientoRepo;

	public List<Viajero> getViajeros() {
		return viajeroRepo.findAll();
	}

	public Optional<Viajero> getViajero(Integer id) {
		return viajeroRepo.findById(id);
	}

	public Viajero save(Viajero viajero) {

		return viajeroRepo.save(viajero);
	}

	public void deleteViajero(Integer id) {
		viajeroRepo.deleteById(id);
	}

	public Optional<Viajero> update(Viajero viajero) {

		Optional<Viajero> viajero2 = viajeroRepo.findById(viajero.getId());

		if (viajero2 != null) {
			viajero2.get().setApellidos(viajero.getApellidos());
			viajero2.get().setDni(viajero.getDni());
			viajero2.get().setNombre(viajero.getNombre());
			viajeroRepo.save(viajero2.get());
		}
		return viajero2;
	}

	public List<Tren> getTrenes() {
		return trenRepo.findAll();
	}

	public Optional<Tren> getTren(Integer id) {
		return trenRepo.findById(id);
	}

	public Tren save(Tren tren) {

		return trenRepo.save(tren);
	}

	public void deleteTren(Integer id) {
		trenRepo.deleteById(id);
	}

	public Optional<Tren> update(Tren tren) {

		Optional<Tren> tren2 = trenRepo.findById(tren.getId());

		if (tren2 != null) {
			tren2.get().setMatricula(tren.getMatricula());
			tren2.get().setViajeros(tren.getViajeros());

			trenRepo.save(tren2.get());
		}
		return tren2;
	}

	public Vagon save(Vagon vagon) {

		return vagonRepo.save(vagon);
	}

	public Asiento save(Asiento asiento) {

		return asientoRepo.save(asiento);
	}

}
