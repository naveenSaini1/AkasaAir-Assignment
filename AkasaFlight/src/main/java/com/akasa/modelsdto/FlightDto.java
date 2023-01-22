package com.akasa.modelsdto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
	private String origin;
	private String destination;
	private LocalDate flightDate;
	private Integer basicFair;


}
