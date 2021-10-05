package com.busticketbooking.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.busticketbooking.controller.BookTicketController;
import com.busticketbooking.dao.BusDao;
import com.busticketbooking.dao.RouteDao;
import com.busticketbooking.dto.BusDto;
import com.busticketbooking.dto.RouteDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.DatabaseException;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.service.BusService;
import com.busticketbooking.util.mapper.BusMapper;
import com.busticketbooking.util.mapper.RouteMapper;

@Service
public class BusServiceImpl implements BusService {
	private static final Logger logger = LogManager.getLogger(BusServiceImpl.class.getName());

	@Autowired
	private BusDao busDao;

	@Autowired
	private RouteDao routeDao;

	@Override
	public Bus getBusById(Long busId) {

		logger.info("Entering Get bus by id function in service layer");

		try {
			if (busDao.isBusExists(busId)) {
				return busDao.getBusById(busId);
			} else {
				throw new BusinessLogicException("Bus with bus Id : " + busId + " Not found");
			}
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public boolean isBusExists(Long busId) {
		try {

			if (busDao.isBusExists(busId))
				return busDao.isBusExists(busId);
			else
				throw new BusinessLogicException("Bus with Bus Id : " + busId + " Not found");

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Bus> getAllBuses() {
		logger.info("Entering Get bus in service layer");

		try {
			if (busDao.getAllBuses() != null)
				return busDao.getAllBuses();
			else
				throw new BusinessLogicException("Buses not found");

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String addBus(BusDto busDto) {

		logger.info("Entering Add bus in service layer");
		try {
			Bus bus = BusMapper.dtoToEntity(busDto);
			Route route = routeDao.getRouteById(bus.getRoute().getRouteId());

			bus.setRoute(route);
			return busDao.addBus(bus);
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String deleteBus(Long id) {

		logger.info("Entering Delete bus in service layer");
		try {
			if (busDao.isBusExists(id))
				return busDao.deleteBus(id);
			else
				throw new BusinessLogicException("Bus with bus Id : " + id + " Not found");

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateBus(BusDto busDto) {

		logger.info("Entering Update bus in service layer");
		try {
			long busId = busDto.getId();
			if (busDao.isBusExists(busId)) {
				Bus bus = BusMapper.dtoToEntity(busDto);
				Route route = routeDao.getRouteById(bus.getRoute().getRouteId());
				bus.setRoute(route);
				return busDao.updateBus(bus);
			} else {
				throw new BusinessLogicException("Bus with bus Id : " + busId + " Not found");
			}
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Bus> getBusByFromAndToLocation(String fromLocation, String toLocation, Date date) {

		logger.info("Entering Get bus by from and to location in service layer");
		try {
			Route route = routeDao.getRouteByFromAndToLocation(fromLocation, toLocation);
			if (route != null)
				return busDao.getBusByFromAndToLocation(route, date);
			else
				throw new BusinessLogicException("Buses not found");

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public Bus getBusByBusName(String busName) {

		logger.info("Entering Get bus by name in service layer");
		try {
			if (busDao.getBusByBusName(busName) != null)
				return busDao.getBusByBusName(busName);
			else
				throw new BusinessLogicException("Buses with busname" + busName + "not found");

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}

	}

}
