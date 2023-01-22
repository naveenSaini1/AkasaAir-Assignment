package com.akasa.modelsdto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.FutureOrPresent;

import com.akasa.models.passengersDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightBookDto {

		
	private String origin;
	private String destination;
	private LocalDate flightDate;
		private List<PassengerDetailsDto> passengersDetailsDtos;
}
