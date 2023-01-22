package com.akasa.repo;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akasa.models.Flight;
@Repository
public interface FlightRepo extends JpaRepository<Flight, Integer> {
	public Optional<Flight> findByOriginAndDestinationAndFlightDate(String origin,String destination,LocalDate flightDate);

	
}
