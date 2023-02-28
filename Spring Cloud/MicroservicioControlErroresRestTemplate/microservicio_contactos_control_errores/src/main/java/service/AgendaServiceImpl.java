package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AgendaDao;
import model.Contacto;

@Service
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	AgendaDao dao;
	@Override
	public void agregarContacto(Contacto contacto) throws Exception {
		//añade el contacto si no existe otro con el mismo email
		//si ya existe uno, provoca una excepción
		if(dao.recuperarContacto(contacto.getEmail())==null) {
			dao.agregarContacto(contacto);	
			return;
		}
		throw new Exception("Contacto repetido");
	}

	@Override
	public List<Contacto> recuperarContactos() {
		return dao.devolverContactos();
	}

	@Override
	public void actualizarContacto(Contacto contacto) {
		//elimina el contacto si existe
		if(dao.recuperarContacto(contacto.getIdContacto())!=null) {
			dao.actualizarContacto(contacto);
		}

	}

	@Override
	public boolean eliminarContacto(int idContacto) {
		if(dao.recuperarContacto(idContacto)!=null) {
			dao.eliminarContacto(idContacto);
			return true;
		}
		return false;
	}

	@Override
	public Contacto buscarContacto(int idContacto) {
		return dao.recuperarContacto(idContacto);
	}

}
