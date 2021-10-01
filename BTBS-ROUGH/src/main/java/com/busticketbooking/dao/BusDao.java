package com.busticketbooking.dao;

import java.util.Date;
import java.util.List;

import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;

public interface BusDao {

	public Bus getBusById(Long busId);
	
	public boolean isBusExists(Long busId);
	
	public List<Bus> getAllBuses();
	
	public  String addBus(Bus bus);
	
	public String deleteBus(Long id);
	
	public String deleteBusByRouteId(Route route);
	
	
	public String updateBus(Bus bus);
	
	public List<Bus> getBusByFromAndToLocation(Route route,Date date);
	
	public Bus getBusByBusName(String busName);
}
