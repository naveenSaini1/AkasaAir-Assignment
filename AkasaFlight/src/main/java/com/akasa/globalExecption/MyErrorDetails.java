package com.akasa.globalExecption;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyErrorDetails {
	private String name;
	private LocalDate timeStamp;

}
