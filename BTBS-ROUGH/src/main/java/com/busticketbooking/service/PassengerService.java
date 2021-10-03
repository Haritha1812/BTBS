package com.busticketbooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.busticketbooking.dto.BusDto;
import com.busticketbooking.dto.PassengerDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Passenger;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.IdNotFoundException;

public interface PassengerService {

	
	public String addPassenger(PassengerDto dto);
	public String deletePassenger(Long id) throws BusinessLogicException;
	public boolean isPassengerExists(Long id);
	public List<Passenger> getPassengerByBusIdAndCusId(Long busid,Long cusid);
	public List<Passenger> getPassengerByCusId(Long cusid);
	public List<Passenger> getAllPassenger() throws BusinessLogicException;
}
