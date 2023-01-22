package com.akasa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akasa.models.passengersDetails;

@Repository
public interface PassengerDetailsRepo extends JpaRepository<passengersDetails, Integer> {

}
