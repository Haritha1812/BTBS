package com.busticketbooking.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.busticketbooking.dao.BusDao;
import com.busticketbooking.dao.RouteDao;
import com.busticketbooking.dto.BusDto;
import com.busticketbooking.dto.RouteDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.service.BusService;
import com.busticketbooking.util.mapper.BusMapper;
import com.busticketbooking.util.mapper.RouteMapper;

@Service
public class BusServiceImpl implements BusService {

	@Autowired
	private BusDao busDao;

	@Autowired
	private RouteDao routeDao;

	@Override
	public Bus getBusById(Long busId) {
		if (busDao.isBusExists(busId)) {
			return busDao.getBusById(busId);
		} else {
			throw new IdNotFoundException("Bus with bus Id : " + busId + " Not found");
		}
	}

	@Override
	public boolean isBusExists(Long busId) {
		return busDao.isBusExists(busId);
	}

	@Override
	public List<Bus> getAllBuses() {

		return busDao.getAllBuses();
	}

	@Override
	public String addBus(BusDto busDto) {
		Bus bus = BusMapper.dtoToEntity(busDto);
        Route route = routeDao.getRouteById(bus.getRoute().getRouteId());
		System.out.println("bus service called....");
		bus.setRoute(route);
		return busDao.addBus(bus);
	}

	@Override
	public String deleteBus(Long id) {
		if (busDao.isBusExists(id))
			return busDao.deleteBus(id);
		else
			throw new IdNotFoundException("Bus with bus Id : " + id + " Not found");

	}

	@Override
	public String updateBus(BusDto busDto) {
		long busId = busDto.getId();
		if (busDao.isBusExists(busId)) {
			Bus bus = BusMapper.dtoToEntity(busDto);
			Route route = routeDao.getRouteById(bus.getRoute().getRouteId());
			bus.setRoute(route);
			return busDao.updateBus(bus);
		} else {
			throw new IdNotFoundException("Bus with bus Id : " + busId + " Not found");
		}
	}

	@Override
	public List<Bus> getBusByFromAndToLocation(String fromLocation, String toLocation, Date date) {
	

		Route route = routeDao.getRouteByFromAndToLocation(fromLocation, toLocation);
		System.out.println(route);
		return busDao.getBusByFromAndToLocation(route,date);
	
	}

	@Override
	public Bus getBusByBusName(String busName) {
		
		return busDao.getBusByBusName(busName);
	}

	
}
