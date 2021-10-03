package com.busticketbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busticketbooking.dto.PassengerDto;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.entity.Passenger;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.DatabaseException;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.response.HttpResponseStatus;
import com.busticketbooking.service.PassengerService;

import static com.busticketbooking.util.BusTicketBookingConstants.RETRIVE;;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("Passenger")
public class PassengerController {
	String message;

	@Autowired
	PassengerService passengerService;

	@PostMapping()
	public ResponseEntity<HttpResponseStatus> add(@RequestBody PassengerDto passengerDto) {

		try {
			System.out.println(passengerDto);
			message = passengerService.addPassenger(passengerDto);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deletepass/{id}")
	public ResponseEntity<HttpResponseStatus> delete(@PathVariable Long id) {
		try {
			message = passengerService.deletePassenger(id);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<List<Passenger>> getall() {

		return new ResponseEntity<>(passengerService.getAllPassenger(), HttpStatus.OK);
	}

	@GetMapping("/details/{busid}/{cusid}")
	public ResponseEntity<HttpResponseStatus> getbybusandcusid(@PathVariable Long busid, @PathVariable Long cusid) {

		try {
			List<Passenger> passengers = passengerService.getPassengerByBusIdAndCusId(busid, cusid);
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVE, passengers), HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/cus/{cusid}")
	public ResponseEntity<HttpResponseStatus> getbcusid(@PathVariable Long cusid) {

		try {
			List<Passenger> passengers = passengerService.getPassengerByCusId(cusid);
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVE, passengers), HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	// EXCEPTION HANDLER FOR BUSSINESSLOGICEXCEPTION.
	@ExceptionHandler(BusinessLogicException.class)
	public ResponseEntity<HttpResponseStatus> bussinessException(BusinessLogicException e) {
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	// EXCEPTION HANDLER FOR DATABASEEXCEPTION.
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<HttpResponseStatus> dataBaseException(DatabaseException e) {
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
}
