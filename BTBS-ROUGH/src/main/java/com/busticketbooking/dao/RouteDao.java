package com.busticketbooking.dao;

import java.util.List;

import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;

public interface RouteDao {
	public Route getRouteById(Long routeId);

	public boolean isRouteExists(Long routeId);

	public List<Route> getAllRoutes();

	public String deleteRoute(Long id);

	public String addRoute(Route route);
	
	public String updateRoute(Route route);
	
	public Route getRouteByName(String routeName);
	
	public Route getRouteByFromAndToLocation(String fromLocation,String toLocation);
}
