package com.busticketbooking.dao;

import java.util.Date;
import java.util.List;

import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;

public interface BusDao {
	/**
	 * get bus by id
	 * 
	 * @param busId
	 * @return
	 */
	public Bus getBusById(Long busId);

	/**
	 * check whether the bus id exists
	 * 
	 * @param busId
	 * @return
	 */
	public boolean isBusExists(Long busId);

	/**
	 * get all buses
	 * 
	 * @return
	 */
	public List<Bus> getAllBuses();

	/**
	 * adding bus
	 * 
	 * @param bus
	 * @return
	 */
	public String addBus(Bus bus);

	/**
	 * deleting bus
	 * 
	 * @param id
	 * @return
	 */
	public String deleteBus(Long id);

	/**
	 * delete bus by route id
	 * 
	 * @param route
	 * @return
	 */
	public String deleteBusByRouteId(Route route);

	/**
	 * updating bus
	 * 
	 * @param bus
	 * @return
	 */
	public String updateBus(Bus bus);

	/**
	 * get bus by from and to location
	 * 
	 * @param route
	 * @param date
	 * @return
	 */
	public List<Bus> getBusByFromAndToLocation(Route route, Date date);

	/**
	 * get bus by busname
	 * 
	 * @param busName
	 * @return
	 */
	public Bus getBusByBusName(String busName);
}
