package com.busticketbooking.controller;

import java.util.Date;
import java.util.List;

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
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.service.BusService;
import com.busticketbooking.service.RouteService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("route")
public class RouteController {
	@Autowired
	RouteService routeService;
	@GetMapping("/{routeId}")
		public ResponseEntity<Route> getid(@PathVariable Long routeId){
		 return new ResponseEntity<>(routeService.getRouteById(routeId), HttpStatus.OK); 
		}

	@GetMapping("/routeName/{routeName}")
	public ResponseEntity<Route> getroutename(@PathVariable String routeName){
	 return new ResponseEntity<>(routeService.getRouteByName(routeName), HttpStatus.OK); 
	}

	@PutMapping
	public ResponseEntity<String> update(@RequestBody RouteDto routeDto) {
		System.out.println(routeDto);
			return	new ResponseEntity<>(routeService.updateRoute(routeDto), new HttpHeaders(), HttpStatus.OK);	
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody RouteDto routeDto) {
		return new ResponseEntity<String>(routeService.addRoute(routeDto), HttpStatus.OK);	
	}

	@DeleteMapping("/deleteroute/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		System.out.println("delete called......."+id);
		MailSend.sendMail("harithaprabha18@gmail.com", "hello", "how are you");
	return	new ResponseEntity<>(routeService.deleteRoute(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Route>> getall() {
		 return new ResponseEntity<>(routeService.getAllRoutes(), HttpStatus.OK); 
	}
	@GetMapping("/searchByfromTo/{fromLocation}/{toLocation}")
	public ResponseEntity<Route> getdetails(@PathVariable("fromLocation") String fromLocation,
			@PathVariable("toLocation") String toLocation
			) throws NullPointerException {

		return new ResponseEntity<Route>(routeService.getRouteByFromAndToLocation(fromLocation, toLocation),HttpStatus.OK);

	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> userNotFound(NullPointerException e) {
		return new ResponseEntity<>("No Customer Data found", HttpStatus.NOT_FOUND);
	}
}
