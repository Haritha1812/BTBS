package com.busticketbooking.controller;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busticketbooking.dto.RouteDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.DatabaseException;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.response.HttpResponseStatus;
import com.busticketbooking.service.BusService;
import com.busticketbooking.service.RouteService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("route")
public class RouteController {

	private static final Logger logger = LogManager.getLogger(RouteController.class.getName());
	
	@Autowired
	RouteService routeService;
	
	String message;
	@GetMapping("/{routeId}")
		public ResponseEntity<HttpResponseStatus> getid(@PathVariable Long routeId){
		
		 try {      
			 Route route = routeService.getRouteById(routeId);
				return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",route),HttpStatus.OK);

			} catch(BusinessLogicException e) {
				return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
			}
			}
	@GetMapping("/routeName/{routeName}")
	public ResponseEntity<HttpResponseStatus> getroutename(@PathVariable String routeName){
	 
	 try {      
		 Route route =routeService.getRouteByName(routeName);
		
		return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",route),HttpStatus.OK);

	} catch(BusinessLogicException e) {
		return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
	}
	}
	@PutMapping
	public ResponseEntity<HttpResponseStatus> update(@RequestBody RouteDto routeDto) {
		
	 try {    
		 message = routeService.updateRoute(routeDto);
		
		return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),message),HttpStatus.OK);

	} catch(BusinessLogicException e) {
		return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
	}
	}
	
	@PostMapping
	public ResponseEntity<HttpResponseStatus> add(@RequestBody RouteDto routeDto) {
	 try {      message = routeService.addRoute(routeDto);
	
	return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),message),HttpStatus.OK);

} catch(BusinessLogicException e) {
	return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
}
}

	@DeleteMapping("/deleteroute/{id}")
	public ResponseEntity<HttpResponseStatus> delete(@PathVariable Long id){
		 try {      message = routeService.deleteRoute(id);
			
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),message),HttpStatus.OK);

		} catch(BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
		}
	
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getall() {
		try {
		List<Route> routes=routeService.getAllRoutes();
		return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",routes),HttpStatus.OK);

		} catch(BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
		}

	@GetMapping("/searchByfromTo/{fromLocation}/{toLocation}")
	public ResponseEntity<HttpResponseStatus> getdetails(@PathVariable("fromLocation") String fromLocation,
			@PathVariable("toLocation") String toLocation
			) {
try {
	Route routes=routeService.getRouteByFromAndToLocation(fromLocation, toLocation);
	return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",routes),HttpStatus.OK);

} catch(BusinessLogicException e) {
	return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
}
}
	
	// EXCEPTION HANDLER FOR BUSSINESSLOGICEXCEPTION.
	@ExceptionHandler(BusinessLogicException.class)
	public ResponseEntity<HttpResponseStatus> bussinessException (BusinessLogicException e) {
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value() ,e.getMessage()), HttpStatus.BAD_REQUEST);
	}
		
	// EXCEPTION HANDLER FOR DATABASEEXCEPTION.
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<HttpResponseStatus> dataBaseException (DatabaseException e) {
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value() ,e.getMessage()), HttpStatus.BAD_REQUEST);
	}

}
