package com.busticketbooking.dao;

import java.util.List;

import com.busticketbooking.dto.PassengerDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.entity.Passenger;

public interface PassengerDao {
	/**
	 * add passenger
	 * @param passenger
	 * @return
	 */
	public  String addPassenger(Passenger passenger);
	/**
	 * delete passenger
	 * @param id
	 * @return
	 */
	public String deletePassenger(Long id);
	/**
	 * check if passenger exists
	 * @param id
	 * @return
	 */
	public boolean isPassengerExists(Long id);
	/**
	 * get all passenger
	 * @return
	 */
	public List<Passenger> getAllPassenger();
	/**
	 * get passenger by customer id
	 * @param customer
	 * @return
	 */
	public List<Passenger> getPassengerByCusId(Customer customer);
	/**
	 * get all passenger by customer and bus id
	 * @param bus
	 * @param customer
	 * @return
	 */
	public List<Passenger> getPassengerByBusIdAndCusId(Bus bus,Customer customer);
	
}
