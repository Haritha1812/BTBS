package com.busticketbooking.controller;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.busticketbooking.dto.BusDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.response.HttpResponseStatus;
import com.busticketbooking.service.BusService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("bus")
public class BusController {

	private static final Logger logger = LogManager.getLogger(BusController.class.getName());
	@Autowired
	BusService busService;
	String message;

	@GetMapping("/{busId}")
	/**
	 * get bus by id
	 * 
	 * @param busId
	 * @return
	 */
	public ResponseEntity<HttpResponseStatus> getbyid(@PathVariable Long busId) {

		logger.info("Entering Get Bus By Id function");
		try {
			Bus bus = busService.getBusById(busId);
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", bus), HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * getting bus by busname
	 * 
	 * @param busName
	 * @return
	 */
	@GetMapping("/name/{busName}")
	public ResponseEntity<HttpResponseStatus> getbyname(@PathVariable String busName) {
		logger.info("Entering Get Bus By Name function");
		try {
			Bus bus = busService.getBusByBusName(busName);

			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", bus), HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * update bus
	 * 
	 * @param busDto
	 * @return
	 */
	@PutMapping
	public ResponseEntity<HttpResponseStatus> update(@RequestBody BusDto busDto) {
		logger.info("Entering Update Bus By Id function");
		try {
			message = busService.updateBus(busDto);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * adding bus function
	 * 
	 * @param busDto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> add(@RequestBody BusDto busDto) {
		logger.info("Entering Add Bus function");
		try {
			message = busService.addBus(busDto);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * deleting the bus by id
	 * 
	 * @param id
	 * @return
	 * @throws IdNotFoundException
	 */
	@DeleteMapping("/deletebus/{id}")
	public ResponseEntity<HttpResponseStatus> delete(@PathVariable Long id) throws IdNotFoundException {
		logger.info("Entering Delete Bus By Id function");
		try {
			message = busService.deleteBus(id);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * get all buses
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getall() {
		logger.info("Entering Get all Bus function");
		try {
			List<Bus> buses = busService.getAllBuses();
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", buses), HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * get bus by from ,to location and date
	 * 
	 * @param fromLocation
	 * @param toLocation
	 * @param date
	 * @return
	 */
	
	  @GetMapping("/searchByfromTo/{fromLocation}/{toLocation}/{date}") public
	  ResponseEntity<HttpResponseStatus> getdetails(@PathVariable("fromLocation")
	  String fromLocation,
	  
	  @PathVariable("toLocation") String toLocation,
	  
	  @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd")Date date) {
	  logger.info("Entering Get Bus By route and date function"); try {
	  System.out.println(date); List<Bus> buses =
	  busService.getBusByFromAndToLocation(fromLocation, toLocation, date); return
	  new ResponseEntity<HttpResponseStatus>( new
	  HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully",
	  buses), HttpStatus.OK);
	  
	  } catch (BusinessLogicException e) { return new
	  ResponseEntity<HttpResponseStatus>( new
	  HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
	  HttpStatus.NOT_FOUND); } }
	 

	/*
	 * @GetMapping("/searchByfromTo/{fromLocation}/{toLocation}/{date}") public
	 * ResponseEntity<List<Bus>> getdetails(@PathVariable("fromLocation") String
	 * fromLocation,
	 * 
	 * @PathVariable("toLocation") String toLocation,
	 * 
	 * @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
	 * throws NullPointerException { System.out.println("search called...");
	 * System.out.println(date); ResponseEntity<Bus> responseEntity = null; return
	 * new ResponseEntity<>(busService.getBusByFromAndToLocation(fromLocation,
	 * toLocation, date), HttpStatus.OK);
	 * 
	 * }
	 * 
	 * @ExceptionHandler(NullPointerException.class) public ResponseEntity<String>
	 * userNotFound(NullPointerException e) { return new
	 * ResponseEntity<>("No location found", HttpStatus.NOT_FOUND); }
	 */

}
