package com.busticketbooking.dao;

import java.util.List;

import com.busticketbooking.entity.Route;

public interface RouteDao {
	/**
	 * get route by id
	 * 
	 * @param routeId
	 * @return
	 */
	Route getRouteById(Long routeId);

	/**
	 * check if route exists
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
	 * delete route
	 * 
	 * @param id
	 * @return
	 */
	String deleteRoute(Long id);

	/**
	 * add route
	 * 
	 * @param route
	 * @return
	 */
	String addRoute(Route route);

	/**
	 * update route
	 * 
	 * @param route
	 * @return
	 */
	String updateRoute(Route route);

	/**
	 * get route by name
	 * 
	 * @param routeName
	 * @return
	 */
	Route getRouteByName(String routeName);

	/**
	 * get route by from and to location
	 * 
	 * @param fromLocation
	 * @param toLocation
	 * @return
	 */
	Route getRouteByFromAndToLocation(String fromLocation, String toLocation);
}
