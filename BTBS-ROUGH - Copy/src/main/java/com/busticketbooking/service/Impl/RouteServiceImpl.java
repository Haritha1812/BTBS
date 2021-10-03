package com.busticketbooking.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busticketbooking.dao.RouteDao;
import com.busticketbooking.dto.RouteDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.service.RouteService;
import com.busticketbooking.util.mapper.RouteMapper;
@Service
public class RouteServiceImpl implements RouteService{
@Autowired
	RouteDao routeDao;
	

	@Override
	public boolean isRouteExists(Long routeId) {
		
		return routeDao.isRouteExists(routeId);
	}

	@Override
	public List<Route> getAllRoutes() {
		if((routeDao.getAllRoutes()).size()!=0)
		return routeDao.getAllRoutes();
		throw new NullPointerException();
	}

	@Override
	public String deleteRoute(Long id) {
		if(routeDao.isRouteExists(id))
		return routeDao.deleteRoute(id);
		throw new IdNotFoundException("Route with route Id:"+id+" Not Found");
	}

	@Override
	public String addRoute(RouteDto routeDto) {
		Route route = RouteMapper.dtoToEntity(routeDto);
		long id = route.getRouteId();
		String fromLocation = route.getFromLocation();
		String toLocation=route.getToLocation();
		System.out.println(id+fromLocation+toLocation);
		return routeDao.addRoute(route);
		
	}

	@Override
	public Route getRouteById(Long routeId) {
		if(routeDao.isRouteExists(routeId))
		return routeDao.getRouteById(routeId);
		throw new IdNotFoundException("Route with route Id:"+routeId+" Not Found");
	}

	@Override
	public String updateRoute(RouteDto routeDto) {
		Route route = RouteMapper.dtoToEntity(routeDto);
		long id = routeDto.getRouteId();
		if(routeDao.isRouteExists(id))
		return routeDao.updateRoute(route);
		throw new IdNotFoundException("Route with route Id:"+id+" Not Found");
	}

	@Override
	public Route getRouteByFromAndToLocation(String fromLocation, String toLocation) {
		if(routeDao.getRouteByFromAndToLocation(fromLocation, toLocation)!=null)
		return routeDao.getRouteByFromAndToLocation(fromLocation, toLocation);

		return null;
	}

	@Override
	public Route getRouteByName(String routeName) {
		
		return routeDao.getRouteByName(routeName);
	}

}
