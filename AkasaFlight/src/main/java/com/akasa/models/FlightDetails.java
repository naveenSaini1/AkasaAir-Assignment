package com.akasa.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FlightDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String seats;
	private Integer seatsPriceMap;
	private Boolean status;

}
