package com.busticketbooking.controller;

import java.util.List;

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

import com.busticketbooking.dto.BusDto;
import com.busticketbooking.dto.SeatDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Seat;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.service.SeatService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("seat")


public class SeatController {
	@Autowired
	SeatService seatService;
	@GetMapping("/{seatId}")
	public ResponseEntity<Seat> getid(@PathVariable Long seatId) throws IdNotFoundException {

		return new ResponseEntity<>(seatService.getSeatById(seatId), HttpStatus.OK);

	}
	@GetMapping("/seatName/{seatName}")
	public ResponseEntity<Seat> getname(@PathVariable String seatName) throws IdNotFoundException {

		return new ResponseEntity<>(seatService.getSeatByName(seatName), HttpStatus.OK);

	}
	@GetMapping("/status/{seatStatus}")
	public ResponseEntity<List<Seat>> getstatus(@PathVariable String seatStatus) throws IdNotFoundException {

		return new ResponseEntity<>(seatService.getSeatByStatus(seatStatus), HttpStatus.OK);

	}
	@GetMapping("/seats/{id}")
	public ResponseEntity<List<Seat>> getseats(@PathVariable long id) throws IdNotFoundException {

		return new ResponseEntity<>(seatService.getSeatByBusId(id), HttpStatus.OK);

	}
	@PutMapping
	public ResponseEntity<String> update(@RequestBody SeatDto seatDto) throws IdNotFoundException {
		ResponseEntity<String> responseEntity = null;

		responseEntity = new ResponseEntity<String>(seatService.updateSeat(seatDto), HttpStatus.OK);
		return responseEntity;

	}
	@PutMapping("status/{seatName}/{id}")
	public ResponseEntity<String> update(@PathVariable String seatName ,@PathVariable long id) throws IdNotFoundException {
		ResponseEntity<String> responseEntity = null;

		responseEntity = new ResponseEntity<String>(seatService.updateStatus(seatName,id), HttpStatus.OK);
		return responseEntity;

	}

	@PostMapping
	public ResponseEntity<String> add(@RequestBody  SeatDto seatDto) throws IdNotFoundException {
		ResponseEntity<String> responseEntity = null;
        System.out.println("seat controller called....");
		responseEntity = new ResponseEntity<String>(seatService.addSeat(seatDto), HttpStatus.OK);
		return responseEntity;

	}
}
