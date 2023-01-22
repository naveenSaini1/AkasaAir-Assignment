package com.akasa.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {
	@Id
	private String token;
	private LocalDate expire;
	

}
