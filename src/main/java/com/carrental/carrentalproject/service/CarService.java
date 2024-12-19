package com.carrental.carrentalproject.service;


import com.carrental.carrentalproject.entities.Car;
import com.carrental.carrentalproject.manager.CarManager;
import com.carrental.carrentalproject.repos.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements CarManager {

    @Autowired
    private CarRepository carRepository;
    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> showCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCar(Integer id) {
        return carRepository.findCarById(id);
    }

    @Override
    public void deleteCar(Integer id) {
        carRepository.deleteById(id);
    }
    @Override
    public List<Car> getAvailableByName(String name) {
        return carRepository.findCarsByAvailableAndName(true,name);
    }

    @Override
    public List<Car> getAvailableCars() {
        return carRepository.findCarsByAvailable(true);
    }
    @Override
    public List<Car> getCarsbyName(String name) {
        return carRepository.findCarsByName(name);
    }
}
