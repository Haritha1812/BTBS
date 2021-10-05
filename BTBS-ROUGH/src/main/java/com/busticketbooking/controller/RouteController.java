package com.busticketbooking.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busticketbooking.dto.RouteDto;
import com.busticketbooking.entity.Route;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.response.HttpResponseStatus;
import com.busticketbooking.service.RouteService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("route")
public class RouteController {

	private static final Logger logger = LogManager.getLogger(RouteController.class.getName());

	@Autowired
	RouteService routeService;

	String message;

	/**
	 * get route by id
	 * 
	 * @param routeId
	 * @return
	 */
	@GetMapping("/{routeId}")
	public ResponseEntity<HttpResponseStatus> getid(@PathVariable Long routeId) {
		logger.info("Entering Get routes by id function");
		try {
			Route route = routeService.getRouteById(routeId);
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", route), HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * get route by name
	 * 
	 * @param routeName
	 * @return
	 */
	@GetMapping("/routeName/{routeName}")
	public ResponseEntity<HttpResponseStatus> getroutename(@PathVariable String routeName) {
		logger.info("Entering Get routes by name function");
		try {
			Route route = routeService.getRouteByName(routeName);

			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", route), HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * updates route
	 * 
	 * @param routeDto
	 * @return
	 */
	@PutMapping
	public ResponseEntity<HttpResponseStatus> update(@RequestBody RouteDto routeDto) {
		logger.info("Entering Update route  function");
		try {
			message = routeService.updateRoute(routeDto);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * add route
	 * 
	 * @param routeDto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> add(@RequestBody RouteDto routeDto) {
		logger.info("Entering Add route function");
		try {
			message = routeService.addRoute(routeDto);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * delete route by id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deleteroute/{id}")
	public ResponseEntity<HttpResponseStatus> delete(@PathVariable Long id) {
		logger.info("Entering delete route function");
		try {
			message = routeService.deleteRoute(id);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * get all routes
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getall() {
		try {
			logger.info("Entering Get routes  function");
			List<Route> routes = routeService.getAllRoutes();
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", routes),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * get details by from and to location
	 * 
	 * @param fromLocation
	 * @param toLocation
	 * @return
	 */
	@GetMapping("/searchByfromTo/{fromLocation}/{toLocation}")
	public ResponseEntity<HttpResponseStatus> getdetails(@PathVariable("fromLocation") String fromLocation,
			@PathVariable("toLocation") String toLocation) {
		logger.info("Entering Get routes by from and to location function");
		try {
			Route routes = routeService.getRouteByFromAndToLocation(fromLocation, toLocation);
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", routes),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

}
