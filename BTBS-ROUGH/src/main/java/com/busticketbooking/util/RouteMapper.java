package com.busticketbooking.util;
import com.busticketbooking.dto.RouteDto;
import com.busticketbooking.entity.Route;

public class RouteMapper {

	public static Route dtoToEntity(RouteDto r) {
		
		Route route = new Route();
		route.setRouteId(r.getRouteId());
		route.setRouteName(r.getRouteName());
		route.setFromLocation(r.getFromLocation());
		route.setToLocation(r.getToLocation());
		route.setDistance(r.getDistance());
		return route;
		
	}
	
}
