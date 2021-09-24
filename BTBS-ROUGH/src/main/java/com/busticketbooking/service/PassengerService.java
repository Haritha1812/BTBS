package com.busticketbooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.busticketbooking.dto.BusDto;
import com.busticketbooking.dto.PassengerDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Passenger;
import com.busticketbooking.exception.IdNotFoundException;

public interface PassengerService {

	/*
	 * public boolean isPassengerExists(Long id);
	 * 
	 * public List<Passenger> getAllPassenger();
	 * 
	 * public String deletePassenger(Long id);
	 * 
	 * public String addPassenger(PassengerDto dto);
	 * 
	 * public String updatePassenger(PassengerDto dto);
	 * 
	 * public Passenger isPassengerEmailExists(String email);
	 * 
	 * public Passenger getPassengerByMobileNumber(String mobileNumber);
	 */
	
	public String addPassenger(PassengerDto dto);
	public String deletePassenger(Long id) throws IdNotFoundException;
	public boolean isPassengerExists(Long id);
	public List<Passenger> getAllPassenger() throws NullPointerException;
}
