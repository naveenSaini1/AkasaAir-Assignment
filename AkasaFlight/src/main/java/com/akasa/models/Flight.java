package com.akasa.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer flightId;
	private String origin;
	private String destination;
	@FutureOrPresent
	private LocalDate flightDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<FlightDetails> flightDetails;
	
	private Integer basicFair;
	
	

}
