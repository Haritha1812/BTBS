package com.busticketbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busticketbooking.dto.BusDto;
import com.busticketbooking.dto.SeatDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Seat;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.DatabaseException;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.response.HttpResponseStatus;
import com.busticketbooking.service.SeatService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("seat")


public class SeatController {
	@Autowired
	SeatService seatService;
	String message;
	@GetMapping("/{seatId}")
	public ResponseEntity<HttpResponseStatus> getid(@PathVariable Long seatId) throws IdNotFoundException {

	
    try {      
		Seat seat =seatService.getSeatById(seatId);
		return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",seat),HttpStatus.OK);

	} catch(BusinessLogicException e) {
		return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
	}
	}
	@GetMapping("/seatName/{seatName}")
	public ResponseEntity<HttpResponseStatus> getname(@PathVariable String seatName) throws IdNotFoundException {

		  try {      
				Seat seat =seatService.getSeatByName(seatName);
				return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",seat),HttpStatus.OK);

			} catch(BusinessLogicException e) {
				return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
			}
			}
	@GetMapping("/status/{seatStatus}")
	public ResponseEntity<List<Seat>> getstatus(@PathVariable String seatStatus) throws IdNotFoundException {

		return new ResponseEntity<>(seatService.getSeatByStatus(seatStatus), HttpStatus.OK);

	}
	@GetMapping("/seats/{id}")
	public ResponseEntity<HttpResponseStatus> getseats(@PathVariable long id) throws IdNotFoundException {

		

	  try {      
			List<Seat> seat =seatService.getSeatByBusId(id);
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",seat),HttpStatus.OK);

		} catch(BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
		}
	@PutMapping
	public ResponseEntity<String> update(@RequestBody SeatDto seatDto) throws IdNotFoundException {
		ResponseEntity<String> responseEntity = null;

		responseEntity = new ResponseEntity<String>(seatService.updateSeat(seatDto), HttpStatus.OK);
		return responseEntity;

	}
	@PutMapping("status/{seatName}/{id}")
	public ResponseEntity<HttpResponseStatus> update(@PathVariable String seatName ,@PathVariable long id) throws IdNotFoundException {
	
		
	 try {      message =seatService.updateStatus(seatName,id);
	
	return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),message),HttpStatus.OK);

} catch(BusinessLogicException e) {
	return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
}
}


	@PostMapping
	public ResponseEntity<HttpResponseStatus> add(@RequestBody  SeatDto seatDto) throws IdNotFoundException {
		 try {      message =seatService.addSeat(seatDto);
			
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),message),HttpStatus.OK);

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
