package com.carrental.carrentalproject.repos;

import com.carrental.carrentalproject.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Car findCarById(Integer Id);
    List<Car> findCarsByAvailable(boolean available);

    List<Car> findCarsByAvailableAndName(boolean available,String name);


    List<Car> findCarsByName(String name);
}
