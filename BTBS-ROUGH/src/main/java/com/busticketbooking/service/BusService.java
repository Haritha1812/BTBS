package com.busticketbooking.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.busticketbooking.dto.BusDto;
import com.busticketbooking.entity.Bus;

public interface BusService {
	
	List<String> bustype = new ArrayList<String>();
	/**
	 * get bus by id
	 * 
	 * @param busId
	 * @return
	 */
	Bus getBusById(Long busId);

	/**
	 * check whether the bus id exists
	 * 
	 * @param busId
	 * @return
	 */
	boolean isBusExists(Long busId);

	/**
	 * get all buses
	 * 
	 * @return
	 */
	List<Bus> getAllBuses();

	/**
	 * deleting bus
	 * 
	 * @param id
	 * @return
	 */
	String deleteBus(Long id);

	/**
	 * adding bus
	 * 
	 * @param bus
	 * @return
	 */
	String addBus(BusDto busDto);

	/**
	 * updating bus
	 * 
	 * @param busDto
	 * @return
	 */
	String updateBus(BusDto busDto);

	/**
	 * get bus by from and to location
	 * 
	 * @param fromlocation
	 * @param ToLocation
	 * @param date
	 * @return
	 */
	List<Bus> getBusByFromAndToLocation(String fromlocation, String ToLocation, Date date);

	/**
	 * get bus by busname
	 * 
	 * @param busName
	 * @return
	 */
	Bus getBusByBusName(String busName);
	
	/**
	 * get all bustypes
	 * @return
	 */
	List<String> getbustypes();

	/**
	 * updating bus
	 * 
	 * @param busDto
	 * @return
	 */
	String updateBusTimings(BusDto busDto);
}
