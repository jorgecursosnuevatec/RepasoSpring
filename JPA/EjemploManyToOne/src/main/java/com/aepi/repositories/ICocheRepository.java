package com.aepi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aepi.entities.Car;

public interface ICocheRepository extends JpaRepository<Car, Long> {

    Car findById(long id);

    List<Car> findByGarageId(Long departmentId);
}
