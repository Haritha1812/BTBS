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

import com.busticketbooking.dto.BusDto;
import com.busticketbooking.dto.RouteDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.service.BusService;

@CrossOrigin(origins = "http://localhost:62918")

@RestController
@RequestMapping("bus")
public class BusController {

	@Autowired
	BusService busService;

	@GetMapping("/{busId}")
	public ResponseEntity<Bus> getBusById(@PathVariable Long busId) throws IdNotFoundException {

		return new ResponseEntity<>(busService.getBusById(busId), HttpStatus.OK);

	}

	@PutMapping
	public ResponseEntity<String> updateBus(@RequestBody BusDto busDto) throws IdNotFoundException {
		ResponseEntity<String> responseEntity = null;

		responseEntity = new ResponseEntity<String>(busService.updateBus(busDto), HttpStatus.OK);
		return responseEntity;

	}

	@PostMapping
	public ResponseEntity<String> addBus(@RequestBody BusDto busDto) throws IdNotFoundException {
		ResponseEntity<String> responseEntity = null;
System.out.println("bus controller called....");
		responseEntity = new ResponseEntity<String>(busService.addBus(busDto), HttpStatus.OK);
		return responseEntity;

	}
	@DeleteMapping("/deletebus/{id}")
	public ResponseEntity<String> deleteBusById(@PathVariable Long id) throws IdNotFoundException {
		return new ResponseEntity<>(busService.deleteBus(id), new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<Bus>> getAllBuses() {
		return new ResponseEntity<>(busService.getAllBuses(), HttpStatus.OK);
	}

	@GetMapping("/searchByfromTo/{fromLocation}/{toLocation}/{date}")
	public ResponseEntity<List<Bus>> getBusDetails(@PathVariable("fromLocation") String fromLocation,
			@PathVariable("toLocation") String toLocation,
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) throws NullPointerException {
             System.out.println("search called...");
		ResponseEntity<Bus> responseEntity = null;
		return new ResponseEntity<>(busService.getBusByFromAndToLocation(fromLocation, toLocation, date),
				HttpStatus.OK);

	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> userNotFound(NullPointerException e) {
		return new ResponseEntity<>("No location found", HttpStatus.NOT_FOUND);
	}

}
