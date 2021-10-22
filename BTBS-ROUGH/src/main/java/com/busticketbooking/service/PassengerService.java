package com.busticketbooking.service;

import java.util.List;

import com.busticketbooking.dto.PassengerDto;
import com.busticketbooking.entity.Passenger;

public interface PassengerService {

	/**
	 * adding passsenger
	 * 
	 * @param dto
	 * @return
	 */
	String addPassenger(PassengerDto dto);

	/**
	 * delete passenger by id
	 * 
	 * @param id
	 * @return
	 */
	String deletePassenger(Long id);

	/**
	 * check if passenger exists
	 * 
	 * @param id
	 * @return
	 */
	boolean isPassengerExists(Long id);

	/**
	 * getting passenger by customer and bus id
	 * 
	 * @param busid
	 * @param cusid
	 * @return
	 */
	List<Passenger> getPassengerByBusIdAndCusId(Long busid, Long cusid);

	/**
	 * getting passenger by customer id
	 * 
	 * @param cusid
	 * @return
	 */
	List<Passenger> getPassengerByCusId(Long cusid);

	/**
	 * get all passenger details
	 * 
	 * @return
	 */
	List<Passenger> getAllPassenger();
	
	
	
	
}
