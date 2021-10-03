package com.busticketbooking.service;

import java.util.Date;
import java.util.List;

import com.busticketbooking.dao.RouteDao;
import com.busticketbooking.dto.BusDto;
import com.busticketbooking.dto.RouteDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.IdNotFoundException;

public interface BusService {
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
	 * deleting bus
	 * 
	 * @param id
	 * @return
	 */
	public String deleteBus(Long id);

	/**
	 * adding bus
	 * 
	 * @param bus
	 * @return
	 */
	public String addBus(BusDto busDto);

	/**
	 * updating bus
	 * 
	 * @param busDto
	 * @return
	 */
	public String updateBus(BusDto busDto);

	/**
	 * get bus by from and to location
	 * 
	 * @param fromlocation
	 * @param ToLocation
	 * @param date
	 * @return
	 */
	public List<Bus> getBusByFromAndToLocation(String fromlocation, String ToLocation, Date date);

	/**
	 * get bus by busname
	 * 
	 * @param busName
	 * @return
	 */
	public Bus getBusByBusName(String busName);
}
