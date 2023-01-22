package com.akasa.globalExecption;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.akasa.execption.FlightExecption;

@ControllerAdvice
public class GlobalExecption {

	@ExceptionHandler(FlightExecption.class)
	public ResponseEntity<MyErrorDetails> flightExecptionHandller(FlightExecption f){
		return new ResponseEntity<MyErrorDetails>(new MyErrorDetails(f.getMessage(),LocalDate.now()), HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> validationHandller(NoHandlerFoundException e){
		return new ResponseEntity<MyErrorDetails>(new MyErrorDetails(e.getMessage(),LocalDate.now()), HttpStatus.BAD_GATEWAY);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> mainExecptionHandller(Exception e){
		return new ResponseEntity<MyErrorDetails>(new MyErrorDetails(e.getMessage(),LocalDate.now()), HttpStatus.BAD_GATEWAY);
	}
	
	
	

}
