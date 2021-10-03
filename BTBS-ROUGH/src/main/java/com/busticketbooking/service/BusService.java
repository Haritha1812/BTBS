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

public Bus getBusById(Long busId) throws BusinessLogicException;
	
	public boolean isBusExists(Long busId);
	
	public List<Bus> getAllBuses();

	public String deleteBus(Long id)throws BusinessLogicException;
	
	public  String addBus(BusDto busDto);
	
	public String updateBus(BusDto busDto)throws BusinessLogicException;
	
	public List<Bus> getBusByFromAndToLocation(String fromlocation,String ToLocation,Date date)throws BusinessLogicException;

    public Bus getBusByBusName(String busName);
}
