package com.busticketbooking.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BusTicketBookingAppApplication;
import com.busticketbooking.dto.BookTicketDto;
import com.busticketbooking.dto.BusDto;
import com.busticketbooking.entity.BookTicket;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.service.BookTicketService;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("bookTicket")
public class BookTicketController {

	private static final Logger logger = LogManager.getLogger(BookTicketController.class.getName());
	@Autowired
	BookTicketService bookTicketService;

	/**
	 * 
	 * @param bookTicketDto
	 * @return string as response entity with status code
	
	 */
	
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody BookTicketDto bookTicketDto) throws IdNotFoundException {
		ResponseEntity<String> responseEntity = null;
		
        logger.info("Entering Booking Ticket Add function");
		responseEntity = new ResponseEntity<String>(bookTicketService.addTicket(bookTicketDto), HttpStatus.OK);
		return responseEntity;

	}
	/**
	 * 
	 * @return list as response entity with status code
	 */
	
	
	@GetMapping
	public ResponseEntity<List<BookTicket>> getall() {
		
		logger.info("Entering Booking Ticket Get all bookings function");
		return new ResponseEntity<>(bookTicketService.getAllTickets(), HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * @return BookTicket object as response entity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<BookTicket> getbyid(@PathVariable Long id) {
		logger.info("Entering Booking Ticket Get bookings by Id function");
		return new ResponseEntity<>(bookTicketService.getTicketById(id), HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * @return BookTicket list as response entity
	 */
	
	@GetMapping("/cus/{id}")
	public ResponseEntity<List<BookTicket>> getbycusid(@PathVariable Long id) {
		logger.info("Entering Booking Ticket Get bookings by Customer Id function");
		return new ResponseEntity<>(bookTicketService.getTicketByCusId(id), HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * @param bid
	 * @param cid
	 * @return string as response entity with status code
	 * @throws IdNotFoundException
	 */
	
	@PutMapping("status/{id}/{bid}/{cid}")
	public ResponseEntity<String> update(@PathVariable long id ,@PathVariable long bid,@PathVariable long cid) throws IdNotFoundException {
		ResponseEntity<String> responseEntity = null;
		logger.info("Entering Booking Ticket Update bookings by Booking status function");
		responseEntity = new ResponseEntity<String>(bookTicketService.updateBookingStatus(id,bid,cid), HttpStatus.OK);
		return responseEntity;

	}

}
