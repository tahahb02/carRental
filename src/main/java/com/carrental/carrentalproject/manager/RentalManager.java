package com.carrental.carrentalproject.manager;

import com.carrental.carrentalproject.entities.Rental;

import java.util.List;

public interface RentalManager {

   Rental SaveRent(Rental Rent);

   List<Rental> getUserRental(Integer id);

   List<Rental> getAll();

   Rental getRental(Integer id);


}
