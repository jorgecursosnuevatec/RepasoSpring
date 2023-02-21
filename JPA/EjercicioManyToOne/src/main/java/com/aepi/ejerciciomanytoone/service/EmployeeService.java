package com.aepi.ejerciciomanytoone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aepi.ejerciciomanytoone.entity.Department;
import com.aepi.ejerciciomanytoone.entity.Employee;
import com.aepi.ejerciciomanytoone.repository.DepartmentRepository;
import com.aepi.ejerciciomanytoone.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	public Object list() {
		return employeeRepository.findAll();
	}

	public Employee save(Employee employee) {
		employee.getAddress().setEmployee(employee);
		Department department = departmentRepository.getById(employee.getDepartment().getId());
		employee.setDepartment(department);
		return employeeRepository.save(employee);
	}

	public Employee get(long id) {
		return employeeRepository.findById(id);
	}

	public Employee update(Employee employee) {
		Employee old = employeeRepository.findById(employee.getId());
		old.setName(employee.getName());
		old.setEmail(employee.getEmail());
		old.setPassword(employee.getPassword());
		old.getAddress().setAddress(employee.getAddress().getAddress());
		employee = employeeRepository.save(old);
		return employee;
	}

	public void delete(long id) {
		employeeRepository.deleteById(id);
	}

	public List<Employee> list(long departmentId) {
		return employeeRepository.findByDepartmentId(departmentId);
	}
}
