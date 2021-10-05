package com.busticketbooking.service;

import java.util.List;

import com.busticketbooking.dto.RouteDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.IdNotFoundException;

public interface RouteService {
	/**
	 * check if route already exists
	 * 
	 * @param routeId
	 * @return
	 */
	boolean isRouteExists(Long routeId);

	/**
	 * get all routes
	 * 
	 * @return
	 */
	List<Route> getAllRoutes();

	/**
	 * delete route by id
	 * 
	 * @param id
	 * @return
	 */
	String deleteRoute(Long id);

	/**
	 * add route
	 * 
	 * @param routeDto
	 * @return
	 */
	String addRoute(RouteDto routeDto);

	/**
	 * get route by id
	 * 
	 * @param busId
	 * @return
	 */
	Route getRouteById(Long busId);

	/**
	 * get route by name
	 * 
	 * @param routeName
	 * @return
	 */
	Route getRouteByName(String routeName);

	/**
	 * update route
	 * 
	 * @param routeDto
	 * @return
	 */
	String updateRoute(RouteDto routeDto);

	/**
	 * get route by from and to location
	 * 
	 * @param fromLocation
	 * @param toLocation
	 * @return
	 */
	Route getRouteByFromAndToLocation(String fromLocation, String toLocation);
}
