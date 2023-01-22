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
public class passengersDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer PassengerId;
	private String firstName;
	private String lastName;
	private String seat;
	

}
