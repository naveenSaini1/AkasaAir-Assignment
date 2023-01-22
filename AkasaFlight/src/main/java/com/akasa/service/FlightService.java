package com.akasa.service;

import com.akasa.execption.FlightExecption;
import com.akasa.models.Flight;
import com.akasa.modelsdto.FlightBookDto;
import com.akasa.modelsdto.FlightBookPnrDto;
import com.akasa.modelsdto.FlightDto;
import com.akasa.modelsdto.FlightResponseDto;
import com.akasa.modelsdto.RetrivePassengerPnrDto;

public interface FlightService {

	
	public Flight ragisterAFlight(FlightDto flightDto) throws FlightExecption;
	public Flight findFlight(FlightResponseDto flightResponseDto) throws FlightExecption;
	public FlightBookPnrDto bookFlight(FlightBookDto flightBookDto) throws FlightExecption;
	public RetrivePassengerPnrDto retrivePassengerPnr(String pnr) throws FlightExecption;
}
