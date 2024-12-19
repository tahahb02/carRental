package com.carrental.carrentalproject.service;


import com.carrental.carrentalproject.entities.Rental;
import com.carrental.carrentalproject.manager.RentalManager;
import com.carrental.carrentalproject.repos.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService implements RentalManager {

    @Autowired
    private RentalRepository rentalRepository;
    @Override
    public Rental SaveRent(Rental Rent) {
        return rentalRepository.save(Rent);
    }

    @Override
    public List<Rental> getUserRental(Integer id) {
        return rentalRepository.getRentalsByUserId(id);
    }

    @Override
    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental getRental(Integer id) {
        return rentalRepository.getById(id);
    }
}
