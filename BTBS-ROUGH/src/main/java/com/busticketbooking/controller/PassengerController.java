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
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.service.PassengerService;

@CrossOrigin(origins = "http://localhost:62918")

@RestController
@RequestMapping("Passenger")
public class PassengerController {

	

	@Autowired
	PassengerService passengerService;
	
	@PostMapping()
	public ResponseEntity<String> addPassenger(@RequestBody PassengerDto passengerDto) {
		
		return new ResponseEntity<String>(passengerService.addPassenger(passengerDto), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deletepass/{id}")
	public ResponseEntity<String> deletePassenger(@PathVariable Long id){
		return	new ResponseEntity<>(passengerService.deletePassenger(id), new HttpHeaders(), HttpStatus.OK);
	
	}
	@GetMapping
	public ResponseEntity<List<Passenger>> getAllPassenger() {
		 
		 return new ResponseEntity<>(passengerService.getAllPassenger(), HttpStatus.OK); 
	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> userNotFound(NullPointerException e) {
		return new ResponseEntity<>("No Passenger Data found", HttpStatus.NOT_FOUND);
}
}
