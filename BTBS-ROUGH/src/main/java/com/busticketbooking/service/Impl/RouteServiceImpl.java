package com.busticketbooking.service.Impl;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busticketbooking.dao.RouteDao;
import com.busticketbooking.dto.RouteDto;
import com.busticketbooking.entity.Route;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.DatabaseException;
import com.busticketbooking.service.RouteService;
import com.busticketbooking.util.mapper.RouteMapper;

@Service
public class RouteServiceImpl implements RouteService {
	@Autowired
	RouteDao routeDao;
	private static final Logger logger = LogManager.getLogger(RouteServiceImpl.class.getName());

	@Override
	public boolean isRouteExists(Long routeId) {
		try {

			if (routeDao.isRouteExists(routeId))
				return routeDao.isRouteExists(routeId);
			throw new BusinessLogicException("Route with route Id:" + routeId + " Not Found");

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Route> getAllRoutes() {

		logger.info("Entering Get Routes in service layer");

		try {
			if ((routeDao.getAllRoutes()).isEmpty())
				throw new BusinessLogicException("No routes found");
			return routeDao.getAllRoutes();
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String deleteRoute(Long id) {

		logger.info("Entering Delete Route in service layer");

		try {
			if (routeDao.isRouteExists(id))
				return routeDao.deleteRoute(id);
			throw new BusinessLogicException("Route with route Id:" + id + " Not Found");
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String addRoute(RouteDto routeDto) {

		logger.info("Entering Add Route in service layer");
		try {
			if (routeDto == null) {

				throw new BusinessLogicException("Route data Not Found");
			}

			Route route = RouteMapper.dtoToEntity(routeDto);

			if (routeDao.getRouteByName(route.getRouteName()) != null) {

				throw new BusinessLogicException("Route Name already Found");
			}
			return routeDao.addRoute(route);

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		} catch (ConstraintViolationException e1) {
			throw new BusinessLogicException("Route already exists");
		}
	}

	@Override
	public Route getRouteById(Long routeId) {

		logger.info("Entering Get Route by id in service layer");
		try {
			if (routeDao.isRouteExists(routeId))
				return routeDao.getRouteById(routeId);
			throw new BusinessLogicException("Route with route Id:" + routeId + " Not Found");
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateRoute(RouteDto routeDto) {

		logger.info("Entering Update Route in service layer");
		try {
			Route route = RouteMapper.dtoToEntity(routeDto);
			long id = routeDto.getRouteId();
			if (routeDao.isRouteExists(id))
				return routeDao.updateRoute(route);
			throw new BusinessLogicException("Route with route Id:" + id + " Not Found");
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public Route getRouteByFromAndToLocation(String fromLocation, String toLocation) {

		logger.info("Entering Get Route by from and to location in service layer");
		try {
			if (routeDao.getRouteByFromAndToLocation(fromLocation, toLocation) != null)
				return routeDao.getRouteByFromAndToLocation(fromLocation, toLocation);
			else

				throw new BusinessLogicException("Route with location Not Found");
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public Route getRouteByName(String routeName) {

		logger.info("Entering Get Route  by route name in service layer");
		try {
			if (routeDao.getRouteByName(routeName) != null)
				return routeDao.getRouteByName(routeName);
			throw new BusinessLogicException("Route with route Name:" + routeName + " Not Found");

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

}
