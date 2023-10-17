package com.carpark.carpark.repository;

import com.carpark.carpark.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByTypeName(String typeName);
}


