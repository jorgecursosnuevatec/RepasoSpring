package com.aepi.ejerciciomanytoone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aepi.ejerciciomanytoone.entity.Department;
import com.aepi.ejerciciomanytoone.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Department> list() {
		return departmentRepository.findAll();
	}

	public Object delete() {
		return null;
	}

	public Department update(Department department) {
		return null;
	}

	public Department save(Department department) {
		return departmentRepository.save(department);
	}

	public Department get(long id) {
		return departmentRepository.getById(id);
	}
}
