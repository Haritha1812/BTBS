package com.busticketbooking.service;

import java.util.List;

import com.busticketbooking.dto.RouteDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.IdNotFoundException;

public interface RouteService {

	public boolean isRouteExists(Long routeId);

	public List<Route> getAllRoutes() ;

	public String deleteRoute(Long id);

	public String addRoute(RouteDto routeDto);

	public Route getRouteById(Long busId);

	public Route getRouteByName(String routeName);
	
	public String updateRoute(RouteDto routeDto);
	
	public Route getRouteByFromAndToLocation(String fromLocation,String toLocation);
}
