package com.busticketbooking.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger logger = LogManager.getLogger(RouteController.class.getName());

	/**
	 * get seat by id
	 * 
	 * @param seatId
	 * @return
	 * @throws IdNotFoundException
	 */
	@GetMapping("/{seatId}")
	public ResponseEntity<HttpResponseStatus> getid(@PathVariable Long seatId) throws IdNotFoundException {
		logger.info("Entering Get Seats by id function");

		try {
			Seat seat = seatService.getSeatById(seatId);
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", seat), HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * get seat by name
	 * 
	 * @param seatName
	 * @return
	 * @throws IdNotFoundException
	 */
	@GetMapping("/seatName/{seatName}")
	public ResponseEntity<HttpResponseStatus> getname(@PathVariable String seatName) throws IdNotFoundException {
		logger.info("Entering Get Seats by name function");
		try {
			Seat seat = seatService.getSeatByName(seatName);
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", seat), HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * get seat by status
	 * 
	 * @param seatStatus
	 * @return
	 * @throws IdNotFoundException
	 */
	@GetMapping("/status/{seatStatus}")
	public ResponseEntity<List<Seat>> getstatus(@PathVariable String seatStatus) throws IdNotFoundException {
		logger.info("Entering Get Seats by status function");
		return new ResponseEntity<>(seatService.getSeatByStatus(seatStatus), HttpStatus.OK);

	}

	/**
	 * get seats by id
	 * 
	 * @param id
	 * @return
	 * @throws IdNotFoundException
	 */
	@GetMapping("/seats/{id}")
	public ResponseEntity<HttpResponseStatus> getseats(@PathVariable long id) throws IdNotFoundException {

		logger.info("Entering Get Seats function");

		try {
			List<Seat> seat = seatService.getSeatByBusId(id);
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", seat), HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping
	public ResponseEntity<HttpResponseStatus> update(@RequestBody SeatDto seatDto) throws IdNotFoundException {
		logger.info("Entering Update Seat by id function");

		try {
			message = seatService.updateSeat(seatDto);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/status/{seatName}/{id}")
	public ResponseEntity<HttpResponseStatus> update(@PathVariable String seatName, @PathVariable long id)
			throws IdNotFoundException {

		logger.info("Entering Get Seats by status function");
		try {
			message = seatService.updateStatus(seatName, id);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<HttpResponseStatus> add(@RequestBody SeatDto seatDto) throws IdNotFoundException {
		logger.info("Entering add Seats function");
		try {
			message = seatService.addSeat(seatDto);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

}
