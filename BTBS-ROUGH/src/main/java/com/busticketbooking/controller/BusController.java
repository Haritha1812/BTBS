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

import com.busticketbooking.dto.BusDto;
import com.busticketbooking.dto.RouteDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.DatabaseException;
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
	public ResponseEntity<HttpResponseStatus> getbyid(@PathVariable Long busId) throws IdNotFoundException {
        try {      
		Bus bus =busService.getBusById(busId);
		return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",bus),HttpStatus.OK);

	} catch(BusinessLogicException e) {
		return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
	}
	}
	
	@GetMapping("/name/{busName}")
	public ResponseEntity<HttpResponseStatus> getbyname(@PathVariable String busName) throws IdNotFoundException {
		
				 try {      Bus bus =busService.getBusByBusName(busName);
							
						return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",bus),HttpStatus.OK);

					} catch(BusinessLogicException e) {
						return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
					}
					}

	@PutMapping
	public ResponseEntity<HttpResponseStatus> update(@RequestBody BusDto busDto) throws IdNotFoundException {
		
		 try {      message = busService.updateBus(busDto);
			
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),message),HttpStatus.OK);

		} catch(BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
		}
	

	@PostMapping
	public ResponseEntity<HttpResponseStatus> add(@RequestBody BusDto busDto) throws IdNotFoundException {
		 try {      message = busService.addBus(busDto);
			
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),message),HttpStatus.OK);

		} catch(BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
		}
	@DeleteMapping("/deletebus/{id}")
	public ResponseEntity<HttpResponseStatus> delete(@PathVariable Long id) throws IdNotFoundException {
		 try {      message = busService.deleteBus(id);
			
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),message),HttpStatus.OK);

		} catch(BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
		}
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getall() {
		
		   try {      List<Bus> buses =busService.getAllBuses();
				return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",buses),HttpStatus.OK);

			} catch(BusinessLogicException e) {
				return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
			}
			}

	@GetMapping("/searchByfromTo/{fromLocation}/{toLocation}/{date}")
	public ResponseEntity<HttpResponseStatus> getdetails(@PathVariable("fromLocation") String fromLocation,
			@PathVariable("toLocation") String toLocation,
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) throws NullPointerException {
		
		   try {      List<Bus> buses =busService.getBusByFromAndToLocation(fromLocation, toLocation, date);
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",buses),HttpStatus.OK);

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
