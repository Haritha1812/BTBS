package com.busticketbooking.dao;

import java.util.List;

import com.busticketbooking.dto.PassengerDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.entity.Passenger;

public interface PassengerDao {
	public  String addPassenger(Passenger passenger);
	public String deletePassenger(Long id);
	public boolean isPassengerExists(Long id);
	public List<Passenger> getAllPassenger();
	public List<Passenger> getPassengerByCusId(Customer customer);
	public List<Passenger> getPassengerByBusIdAndCusId(Bus bus,Customer customer);
	/*
	 * public boolean isPassengerExists(Long id);
	 * 
	 * public List<Passenger> getAllPassenger();
	 * 
	 * public String deletePassenger(Long id);
	 * 
	 * 
	 * 
	 * public String updatePassenger(Passenger passenger);
	 * 
	 * public Passenger isPassengerEmailExists(String email);
	 * 
	 * public Passenger getPassengerByMobileNumber(String mobileNumber);
	 */
}
