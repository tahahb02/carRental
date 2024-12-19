package com.carrental.carrentalproject.controller;


import com.carrental.carrentalproject.entities.Car;
import com.carrental.carrentalproject.entities.Rental;
import com.carrental.carrentalproject.entities.User;
import com.carrental.carrentalproject.service.CarService;
import com.carrental.carrentalproject.service.RentalService;
import com.carrental.carrentalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Period;
import java.time.temporal.ChronoUnit;

@Controller
public class RentalController {

    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;
    @Autowired
    private RentalService rentalService;


    @GetMapping("/user/bookcar/{id}")
    private String AddRent(@PathVariable("id") Integer id , Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userService.getUserByEmail(currentUsername);
        model.addAttribute("rent",new Rental());
        model.addAttribute("user",user.getFullname());
        model.addAttribute("car_",carService.getCar(id));
        return "bookcar";
    }


    @PostMapping("/saveRent")
    private String SaveRent(@ModelAttribute Rental new_rent, @RequestParam Integer id){
        Rental rent = rentalService.SaveRent(new_rent);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userService.getUserByEmail(currentUsername);
        Car car = carService.getCar(id);
        rent.setUser(user);
        rent.setCar(car);
        long daysBetween = Period.between(new_rent.getStartDate(), new_rent.getEndDate()).getDays();
        System.out.println(daysBetween);
        rent.setTotal_price(daysBetween*car.getPrice_rent());
        rent.setConfirmed(false);
        car.setAvailable(false);
        rentalService.SaveRent(rent);

        return "redirect:/user/rentcar";
    }

    @GetMapping("/user/rentcar")
    private String CarRents(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userService.getUserByEmail(currentUsername);
        model.addAttribute("rents",rentalService.getUserRental(user.getId()));
        return "userrent";
    }
}
