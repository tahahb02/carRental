package com.carrental.carrentalproject.controller;


import com.carrental.carrentalproject.entities.Car;
import com.carrental.carrentalproject.entities.Rental;
import com.carrental.carrentalproject.service.CarService;
import com.carrental.carrentalproject.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private RentalService rentalService;

    @GetMapping("/admin/car")
    private String carAdmin(Model model){
        model.addAttribute("cars",carService.showCars());
        model.addAttribute("car", new Car());
        return "carmanager";
    }

    @GetMapping("/admin/rent")
    private String carRents(Model model){
        model.addAttribute("rents",rentalService.getAll());
        return "carrent";
    }

    @PostMapping("/admin/car")
    private String AddCar(@ModelAttribute Car car){
        carService.addCar(car);
        return "redirect:/admin/car";
    }

    @GetMapping("/admin/editcar/{id}")
    private String AddCar(@PathVariable("id") Integer id, Model model){
        model.addAttribute("car",carService.getCar(id));
        return "updatecar";
    }

    @GetMapping("admin/deletecar/{id}")
    private String deleteCar(@PathVariable("id") Integer id){
        carService.deleteCar(id);
        return "redirect:/admin/car";
    }

    @PostMapping("/save")
    private String saveCar(@ModelAttribute Car car){
        carService.addCar(car);
        return "redirect:/admin/car";
    }

    @GetMapping("/admin/confirmrental/{id}")
    private String saveCar(@PathVariable Integer id){
        Rental rent = rentalService.getRental(id);
        rent.setConfirmed(true);
        rentalService.SaveRent(rent);
        return "redirect:/admin/rent";
    }
    @PostMapping("/admin/searchAdmin")
    private String searchCars(@RequestParam String searchkey, Model model){
        model.addAttribute("cars",carService.getCarsbyName(searchkey));
        model.addAttribute("car", new Car());
        return "carmanager";
    }



}
