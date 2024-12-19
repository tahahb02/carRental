package com.carrental.carrentalproject.repos;

import com.carrental.carrentalproject.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    List<Rental> getRentalsByUserId(Integer Id);
}
