package com.aepi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aepi.model.Asignatura;
import com.aepi.model.Curso;
import com.aepi.model.Estudiante;
import com.aepi.model.Matricula;
import com.aepi.repository.IAsignaturaRepo;
import com.aepi.repository.ICursoRepo;
import com.aepi.repository.IEstudianteRepo;
import com.aepi.repository.IMatriculaRepo;

@SpringBootApplication
public class TareaCeroApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TareaCeroApplication.class, args);
	}

	@Autowired
	IAsignaturaRepo asigRepo;

	@Autowired
	IMatriculaRepo matriRepo;

	@Autowired
	ICursoRepo cursoRepo;

	@Autowired
	IEstudianteRepo estuRepo;

	@Override
	public void run(String... args) throws Exception {

		llenarBD();
	}

	private void llenarBD() {
		// +++++++++++++++++++++++++++++++++++++
		// 	ONE TO ONE
		// +++++++++++++++++++++++++++++++++++++
		//
		Matricula mat = new Matricula(null, "21-22");
		mat = matriRepo.save(mat);

		Estudiante estu = new Estudiante(null, "Pedro", "Perez");

		// establecemos la relacion añadiendola al estudiante
		// que creará la columna con el id de la matricula
		estu.setMatricula(mat); // aqui se añade la matricula "Cascade MERGE" lo permite o daría ERROR "detached entity..."
		estuRepo.save(estu);

		// +++++++++++++++++++++++++++++++++++++
		// 	MANY TO ONE
		// +++++++++++++++++++++++++++++++++++++

		// se debe guardar el curso primero
		Curso curso = new Curso(null, "Primero A");
		//cursoRepo.save(curso); 			// sin cascade Persist si no se guarda dará el error "object references an unsaved transient instance"

		// se establece la relación
		estu.setCurso(curso);				// se guardará curso con cascade Persist
		estuRepo.save(estu);

		// +++++++++++++++++++++++++++++++++++++
		// 	MANY TO MANY
		// +++++++++++++++++++++++++++++++++++++
		Asignatura asig = new Asignatura(null, "Informatica");
		asig.addEstudiante(estu);			// se establece la relacion
		
		// al guardar la asignatura se guardarán el resto de objetos
		asigRepo.save(asig);				// cascade MERGE permite que se haya guardado antes Estudiante o daría error "detached entity..."				
		
	}
}