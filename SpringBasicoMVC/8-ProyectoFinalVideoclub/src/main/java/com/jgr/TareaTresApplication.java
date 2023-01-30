package com.jgr;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jgr.entity.Rol;
import com.jgr.repository.RolRepository;

@SpringBootApplication
public class TareaTresApplication {
	
	@Autowired
	private RolRepository repoRol;
	
	//si no estan dados de alta,lo hago yo
	@PostConstruct
	public void altaRoles() {
		
		int longitud =repoRol.findAll().size();
		if(longitud<=0){
			Rol rola =new Rol();
			rola.setNombre("ROLE_ADMIN");
			repoRol.save(rola);
			rola =new Rol();
			rola.setNombre("ROLE_USER");
			repoRol.save(rola);
			
		}
			
	}
	

	public static void main(String[] args) {
		SpringApplication.run(TareaTresApplication.class, args);
	}
	
	
	

}
