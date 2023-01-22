package com.akasa.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FlightBookList {
	@Id
	private String pnrNo;
	private Integer flightId;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<passengersDetails> passengersDetails;
	
	
	
}
