package com.busticketbooking.dao;

import java.util.Date;
import java.util.List;

import com.busticketbooking.dto.BusDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;

public interface BusDao {
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
	 * adding bus
	 * 
	 * @param bus
	 * @return
	 */
	String addBus(Bus bus);

	/**
	 * deleting bus
	 * 
	 * @param id
	 * @return
	 */
	String deleteBus(Long id);

	/**
	 * delete bus by route id
	 * 
	 * @param route
	 * @return
	 */
	String deleteBusByRouteId(Route route);

	/**
	 * updating bus
	 * 
	 * @param bus
	 * @return
	 */
	String updateBus(Bus bus);

	/**
	 * get bus by from and to location
	 * 
	 * @param route
	 * @param date
	 * @return
	 */
	List<Bus> getBusByFromAndToLocation(Route route, Date date);

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
 * update the timings of the bus
 * @param bus
 * @return
 */
	String updateBusTimings(Bus bus);
}
