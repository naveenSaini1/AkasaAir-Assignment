package com.akasa.modelsdto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RetrivePassengerPnrDto {
	private String pnrNo;
	private String origin;
	private String destination;
	private LocalDate flightDate;
	private List<PassengerDetailsDto> passengersDetailsDtos;
}
