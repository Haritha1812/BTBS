package com.busticketbooking.dao;

import java.util.List;

import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;

public interface RouteDao {
	/**
	 * get route by id
	 * @param routeId
	 * @return
	 */
	public Route getRouteById(Long routeId);

	/**
	 * check if route exists
	 * @param routeId
	 * @return
	 */
	public boolean isRouteExists(Long routeId);
   
	/**
	 * get all routes
	 * @return
	 */
	public List<Route> getAllRoutes();

	/**
	 * delete route
	 * @param id
	 * @return
	 */
	public String deleteRoute(Long id);

	/**
	 * add route
	 * @param route
	 * @return
	 */
	public String addRoute(Route route);
	
	/**
	 * update route
	 * @param route
	 * @return
	 */
	public String updateRoute(Route route);
	
	/**
	 * get route by name
	 * @param routeName
	 * @return
	 */
	public Route getRouteByName(String routeName);
	
	/**
	 * get route by from and to location
	 * @param fromLocation
	 * @param toLocation
	 * @return
	 */
	public Route getRouteByFromAndToLocation(String fromLocation,String toLocation);
}
