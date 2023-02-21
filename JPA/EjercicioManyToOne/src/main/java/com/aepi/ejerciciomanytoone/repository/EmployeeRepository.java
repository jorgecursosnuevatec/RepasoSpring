package com.aepi.ejerciciomanytoone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aepi.ejerciciomanytoone.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findById(long id);

    List<Employee> findByDepartmentId(Long departmentId);
}
