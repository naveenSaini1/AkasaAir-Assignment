package com.akasa.modelsdto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDetailsDto {
	private String firstName;
	private String lastName;
	private String seat;
	
}
