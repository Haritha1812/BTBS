package com.busticketbooking.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger logger = LogManager.getLogger(PassengerController.class.getName());
	@Autowired
	PassengerService passengerService;

	/**
	 * add passenger
	 * 
	 * @param passengerDto
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<HttpResponseStatus> add(@RequestBody PassengerDto passengerDto) {
		logger.info("Entering Add Passeger function");

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

	/**
	 * delete passener by id function
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deletepass/{id}")
	public ResponseEntity<HttpResponseStatus> delete(@PathVariable Long id) {
		logger.info("Entering Delete Passenger function");

		try {
			message = passengerService.deletePassenger(id);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * get all passengers
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getall() {
		logger.info("Entering Get Passengers function");
		try {
			List<Passenger> passengers = passengerService.getAllPassenger();
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVE, passengers), HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * get passenger details by bus and customer id
	 * 
	 * @param busid
	 * @param cusid
	 * @return
	 */
	@GetMapping("/details/{busid}/{cusid}")
	public ResponseEntity<HttpResponseStatus> getbybusandcusid(@PathVariable Long busid, @PathVariable Long cusid) {
		logger.info("Entering Get Passenger by customer,bus id function");
		try {
			List<Passenger> passengers = passengerService.getPassengerByBusIdAndCusId(busid, cusid);
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVE, passengers), HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * get passenger by customer is
	 * 
	 * @param cusid
	 * @return
	 */
	@GetMapping("/cus/{cusid}")
	public ResponseEntity<HttpResponseStatus> getbycusid(@PathVariable Long cusid) {
		logger.info("Entering get Passenger by customer id function");
		try {
			List<Passenger> passengers = passengerService.getPassengerByCusId(cusid);
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVE, passengers), HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

}
