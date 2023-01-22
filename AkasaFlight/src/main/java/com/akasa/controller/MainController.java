package com.akasa.controller;

import java.security.PublicKey;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akasa.execption.FlightExecption;
import com.akasa.execption.TokenExecption;
import com.akasa.models.Flight;
import com.akasa.models.Token;
import com.akasa.modelsdto.FlightBookDto;
import com.akasa.modelsdto.FlightBookPnrDto;
import com.akasa.modelsdto.FlightDto;
import com.akasa.modelsdto.FlightResponseDto;
import com.akasa.modelsdto.RetrivePassengerPnrDto;
import com.akasa.repo.TokenRepo;
import com.akasa.service.impl.FlightServiceimpl;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.utility.RandomString;


@RestController
@RequestMapping("/api")
public class MainController {
	
	@Autowired
	private TokenRepo tokenRepo;
	
	private Bucket bucket;
	
	@Autowired
	private FlightServiceimpl flightService;
	
	
	@PostMapping("/ragiflight")
	@ApiOperation("In this api we are adding the flight Details to the database and i added this becuase of testing purpose")
	public ResponseEntity<Flight> ragisterAFlight(@Valid @RequestBody FlightDto flightDto) throws FlightExecption, TokenExecption{
		if(!checkToken()) {
			throw new TokenExecption("Please Ragister Your token || token expire");
		}
		if(!(bucket.tryConsume(1))) {
			throw new TokenExecption("Your Rate Limit Has been Riched");
		}
		return new ResponseEntity<Flight>(flightService.ragisterAFlight(flightDto),HttpStatus.CREATED);
	}
	
	@PostMapping("/flight/details")
	@ApiOperation("In this api we are finding the flight")
	public ResponseEntity<Flight> availableFlight(@RequestBody FlightResponseDto flightResponseDto)throws FlightExecption, TokenExecption{
		if(!checkToken()) {
			throw new TokenExecption("Please Ragister Your token || token expire");
		}
		if(!(bucket.tryConsume(1))) {
			throw new TokenExecption("Your Rate Limit Has been Riched");
		}
		return new ResponseEntity<Flight>(flightService.findFlight(flightResponseDto), HttpStatus.OK);
		
	}
	
	@PostMapping("/flight/book")
	@ApiOperation("In this Api we can book our ticket")
	public ResponseEntity<FlightBookPnrDto> bookFlight(@RequestBody  FlightBookDto flightBookDto) throws FlightExecption, TokenExecption{
		if(!checkToken()) {
			throw new TokenExecption("Please Ragister Your token || token expire");
		}
		if(!(bucket.tryConsume(1))) {
			throw new TokenExecption("Your Rate Limit Has been reached");
		}
		return new ResponseEntity<>(flightService.bookFlight(flightBookDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/flight/retrieve")
	@ApiOperation("In this api we retrive the data of Passenger using Pnr Number")
	public ResponseEntity<RetrivePassengerPnrDto> retrivePassengerPnr(@RequestParam("pnr") String prn , @RequestParam("lastName") String lastName) throws FlightExecption, TokenExecption{
		if(!checkToken()) {
			throw new TokenExecption("Please Ragister Your token || token expire");
		}
		if(!(bucket.tryConsume(1))) {
			throw new TokenExecption("Your Rate Limit Has been Riched");
		}
		return new ResponseEntity<RetrivePassengerPnrDto>(flightService.retrivePassengerPnr(prn),HttpStatus.OK);
	}
	
	@GetMapping("/auth")
	@ApiOperation("we have need a token before access to each and every api and when we create this api our rate limit token will also genrate")
	public ResponseEntity<Token> genrateToken() throws TokenExecption{
		
				return new ResponseEntity<Token>(genrateTokenMethod(),HttpStatus.CREATED);
	}
	
	
	// check token is valid or not
	public boolean checkToken() {
		List<Token> list=tokenRepo.findAll();
		if(list.size()>=1) {
			Token token2=null;
			for(Token i:list) {
				token2=i;
			}
			LocalDate localDate=token2.getExpire();
			if(localDate==null)
				return false;
			String[] arr=localDate.toString().split("-");
			LocalDate nowLocalDate=LocalDate.now();
			String[] arr2=nowLocalDate.toString().split("-");
			int first=Integer.parseInt(arr[2]);
			int sec=Integer.parseInt(arr2[2]);
			if(first==sec) {
				return true;
			}
			else {
				tokenRepo.delete(token2);
			}
			return false;
			
			
		}
		return false;
	}
	public Token genrateTokenMethod() throws TokenExecption {
		List<Token> list=tokenRepo.findAll();
		if(list.size()==0) {
			Token token=new Token();
			token.setToken(RandomString.make(6));
			token.setExpire(LocalDate.now());
			Refill refill=Refill.of(5, Duration.ofMinutes(1));
			 bucket=Bucket4j.builder()
			.addLimit(Bandwidth.classic(5, refill))
			.build();
			
			return tokenRepo.save(token);
		}
		
			return list.get(0);
		
	}

}
