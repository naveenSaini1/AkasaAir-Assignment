package com.akasa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akasa.models.FlightBookList;

@Repository
public interface FlightBookListRepo extends JpaRepository<FlightBookList, String> {

}
