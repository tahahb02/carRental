package com.carrental.carrentalproject.manager;

import com.carrental.carrentalproject.entities.Car;

import java.util.List;

public interface CarManager {

    Car addCar(Car car);
    List<Car> showCars();

    Car getCar(Integer id);

    void deleteCar(Integer id);

    List<Car> getAvailableByName(String name);

    List<Car> getAvailableCars();


    List<Car> getCarsbyName(String name);
}
