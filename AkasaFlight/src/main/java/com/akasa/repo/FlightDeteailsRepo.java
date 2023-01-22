package com.akasa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akasa.models.FlightDetails;
@Repository
public interface FlightDeteailsRepo extends JpaRepository<FlightDetails, Integer> {

}
