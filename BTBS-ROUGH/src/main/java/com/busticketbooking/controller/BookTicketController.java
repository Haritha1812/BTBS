package com.busticketbooking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.busticketbooking.dto.BookTicketDto;
import com.busticketbooking.entity.BookTicket;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.response.HttpResponseStatus;
import com.busticketbooking.service.BookTicketService;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("bookTicket")
public class BookTicketController {

	private static final Logger logger = LogManager.getLogger(BookTicketController.class);
	@Autowired
	BookTicketService bookTicketService;
	String message;

	/**
	 * add the booking data while booking the bus
	 * @param bookTicketDto
	 * @return httpresponsestatus as responsecode and message
	 */

	@PostMapping
	public ResponseEntity<HttpResponseStatus> add(@Valid @RequestBody BookTicketDto bookTicketDto)   {

		logger.info("Entering Booking Ticket Add function");

		try {
			message = bookTicketService.addTicket(bookTicketDto);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * get all booked tickets
	 * @return httpresponsestatus as responsecode , message and list
	 */

	@GetMapping
	public ResponseEntity<HttpResponseStatus> getall() {
		logger.info("Entering Booking Ticket Get all bookings function");
		try {
			List<BookTicket> bookTicket = bookTicketService.getAllTickets();
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", bookTicket),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * get bookticket by id
	 * @param id
	 * @return httpresponsestatus as responsecode , message and bookticket object
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getbyid(@PathVariable Long id) {
		logger.info("Entering Booking Ticket Get bookings by Id function");

		try {
			BookTicket bookTicket = bookTicketService.getTicketById(id);
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", bookTicket),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * get the booking details by customer id
	 * @param id
	 * @return httpresponsestatus as responsecode , message and list
	 */

	@GetMapping("/cus/{id}")
	public ResponseEntity<HttpResponseStatus> getbycusid(@PathVariable Long id) {
		logger.info("Entering Booking Ticket Get bookings by Customer Id function");

		try {
			List<BookTicket> bookTicket = bookTicketService.getTicketByCusId(id);
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", bookTicket),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * get the booking details by customer and bus id
	 * @param id
	 * @param bid
	 * @param cid
	 * @return httpresponsestatus as responsecode , message and list
	 */

	@PutMapping("/status/{id}/{bid}/{cid}")
	public ResponseEntity<HttpResponseStatus> update(@PathVariable long id, @PathVariable long bid,
			@PathVariable long cid)   {

		logger.info("Entering Booking Ticket Update bookings by Booking status function");

		try {
			message = bookTicketService.updateBookingStatus(id, bid, cid);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	
       @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = error.getObjectName();
			String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
