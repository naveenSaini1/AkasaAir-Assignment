package com.akasa.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akasa.execption.FlightExecption;
import com.akasa.models.Flight;
import com.akasa.models.FlightBookList;
import com.akasa.models.FlightDetails;
import com.akasa.models.passengersDetails;
import com.akasa.modelsdto.FlightBookDto;
import com.akasa.modelsdto.FlightBookPnrDto;
import com.akasa.modelsdto.FlightDto;
import com.akasa.modelsdto.FlightResponseDto;
import com.akasa.modelsdto.PassengerDetailsDto;
import com.akasa.modelsdto.RetrivePassengerPnrDto;
import com.akasa.repo.FlightBookListRepo;
import com.akasa.repo.FlightDeteailsRepo;
import com.akasa.repo.FlightRepo;
import com.akasa.repo.PassengerDetailsRepo;
import com.akasa.repo.TokenRepo;
import com.akasa.service.FlightService;

import net.bytebuddy.utility.RandomString;
@Service
public class FlightServiceimpl implements FlightService {
	@Autowired
	private FlightRepo flightRepo;
	@Autowired
	private FlightDeteailsRepo flightDeteailsRepo;
	
	@Autowired
	private FlightBookListRepo flightBookListRepo;
	
	@Autowired
	private PassengerDetailsRepo passengerDetailsRepo;
	
	@Autowired
	private TokenRepo tokenRepo;
	
	@Override
	public Flight ragisterAFlight(FlightDto flightDto) throws FlightExecption {
		
		Optional<Flight> flOptional=flightRepo.findByOriginAndDestinationAndFlightDate(flightDto.getOrigin(), flightDto.getDestination(), flightDto.getFlightDate());
		if(flOptional.isPresent()) {
			throw new FlightExecption("Already Present");
		}
		
		Flight flight=new Flight();
		flight.setBasicFair(flightDto.getBasicFair());
		flight.setDestination(flightDto.getDestination());
		flight.setFlightDate(flightDto.getFlightDate());
		flight.setOrigin(flightDto.getOrigin());
		List<String> seats=new ArrayList<>(Arrays.asList("1A","1B","1C","1D","1E","1F",
				"2A","2B","2C","2D","2E","2F",
				"3A","3B","3C","3D","3E","3F",
				"4A","4B","4C","4D","4E","4F",
				"5A","5B","5C","5D","5E","5F"
				,"6A","6B","6C","6D","6E","6F",
				"7A","7B","7C","7D","7E","7F",
				"8A","8B","8C","8D","8E","8F",
				"9A","9B","9C","9D","9E","9F",
				"10A","10B","10C","10D","10E","10F",
				"11A","11B","11C","11D","11E","11F",
				"12A","12B","12C","12D","12E","12F"
				,"13A","13B","13C","13D","13E","13F",
				"14A","14B","14C","14D","14E","14F",
				"15A","15B","15C","15D","15E","15F",
				"16A","16B","16C","16D","16E","16F",
				"17A","17B","17C","17D","17E","17F",
				"18A","18B","18C","18D","18E","18F",
				"19A","19B","19C","19D","19E","19F",
				"20A","20B","20C","20D","20E","20F"
				,"21A","21B","21C","21D","21E","21F"));
		
		List<Integer> seatPriceMap=new ArrayList<>(Arrays.asList(1500,500,500,500,500,1500,
				1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,1500,500,500,500,500,1500
				,200,500,100,0,200,200));
		
		List<FlightDetails> flightDetails=new ArrayList<>();	
		for(int i=0;i<seatPriceMap.size();i++) {
			FlightDetails flightDetails2=new FlightDetails(0, seats.get(i),seatPriceMap.get(i),false);
			flightDetails.add(flightDeteailsRepo.save(flightDetails2));
		}
		System.out.println(flightDetails.size());
		flight.setFlightDetails(flightDetails);
		return flightRepo.save(flight);
		
	}

	@Override
	public Flight findFlight(FlightResponseDto flightResponseDto) throws FlightExecption {
		Optional<Flight> flightOptional= flightRepo.findByOriginAndDestinationAndFlightDate(flightResponseDto.getOrigin(), flightResponseDto.getDestination(), flightResponseDto.getFlightDate());
		if(flightOptional.isPresent()) {
			return flightOptional.get();
		}
		throw new FlightExecption("Not found");
		
	}

	@Override
	public FlightBookPnrDto bookFlight(FlightBookDto flightBookDto) throws FlightExecption {
		Optional<Flight> flightOptional= flightRepo.findByOriginAndDestinationAndFlightDate(flightBookDto.getOrigin(),flightBookDto.getDestination(),flightBookDto.getFlightDate());
		if(flightOptional.isPresent()) {
			Boolean flag=false;
			FlightBookPnrDto flightBookPnrDto=new FlightBookPnrDto();
			Flight flight=flightOptional.get();
			int total=flight.getBasicFair();
			int count=0;
			List<FlightDetails> flightDetails=flight.getFlightDetails();
			System.out.println(flightDetails.size());
			for(FlightDetails i:flightDetails) {
				for(PassengerDetailsDto j:flightBookDto.getPassengersDetailsDtos()) {
					if(i.getSeats().equals(j.getSeat()) && i.getStatus()==false) {
						i.setStatus(true);
						flag=true;
						count++;
						total+=i.getSeatsPriceMap();
					}
				}
			}
			if(flightBookDto.getPassengersDetailsDtos().size()==count) {
				
			}
			else {
				throw new FlightExecption("Seats Not found");
			}
			
			if(flag==false) {
				throw new FlightExecption("Seats Not found");
			}
			else {
				FlightBookList flightBookList=new FlightBookList();
				flightBookList.setFlightId(flight.getFlightId());
				String pnrString=generatePassword(5);
				flightBookPnrDto.setPnrNo(pnrString);
				flightBookList.setPnrNo(pnrString);
				
				List<passengersDetails> newList=new ArrayList<>();
				List<PassengerDetailsDto> list=flightBookDto.getPassengersDetailsDtos();
				System.out.println(list.size());
				for(PassengerDetailsDto i:list) {
					passengersDetails passengersDetails=new passengersDetails(0,i.getFirstName(),i.getLastName(),i.getSeat());
					newList.add(passengerDetailsRepo.save(passengersDetails));
					
				}
				flightBookList.setPassengersDetails(newList);
				flightBookListRepo.save(flightBookList);
				flightRepo.save(flight);
				flightBookPnrDto.setTotal(total);
				return flightBookPnrDto;
				
			}
		}
		throw new FlightExecption("No flight found");
		
	}
	
	
	 
	 public static String generatePassword(int length) {
		    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		    SecureRandom rnd = new SecureRandom();
		    char[] password = new char[length];
		    for (int i = 0; i < length; i++) {
		      password[i] = characters.charAt(rnd.nextInt(characters.length()));
		    }
		    return new String(password);
		  }

	@Override
	public RetrivePassengerPnrDto retrivePassengerPnr(String pnr) throws FlightExecption {
		Optional<FlightBookList> fOptional=flightBookListRepo.findById(pnr);
		
		if(fOptional.isPresent()) {
			Optional<Flight> flightOptional=flightRepo.findById(fOptional.get().getFlightId());
			Flight flight=flightOptional.get();
			RetrivePassengerPnrDto retrivePassengerPnrDto=new RetrivePassengerPnrDto();
			retrivePassengerPnrDto.setPnrNo(pnr);
			retrivePassengerPnrDto.setDestination(flight.getDestination());
			retrivePassengerPnrDto.setFlightDate(flight.getFlightDate());
			retrivePassengerPnrDto.setOrigin(flight.getOrigin());
			List<passengersDetails> list=fOptional.get().getPassengersDetails();
			List<PassengerDetailsDto> newList=new ArrayList<>();
			for(passengersDetails i:list) {
				PassengerDetailsDto passengerDetailsDto=new PassengerDetailsDto(i.getFirstName(),i.getLastName(),i.getSeat());
				newList.add(passengerDetailsDto);
			}
			retrivePassengerPnrDto.setPassengersDetailsDtos(newList);
			return retrivePassengerPnrDto;
			
		}
		throw new FlightExecption("pnr not found");
	}
}
