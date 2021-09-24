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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busticketbooking.dto.CustomerDto;
import com.busticketbooking.dto.PassengerDto;
import com.busticketbooking.entity.Customer;

import com.busticketbooking.exception.DuplicateEmailException;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.exception.ResourceNotFoundException;
import com.busticketbooking.service.CustomerService;
import com.busticketbooking.service.PassengerService;


@CrossOrigin(origins = "http://localhost:62918")

@RestController
@RequestMapping("Customer")
public class CustomerController   {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	PassengerService passengerService;
	
	@GetMapping("/email/{email}")
	public ResponseEntity<Customer> getCustomertByEmail(@PathVariable("email") String email)throws DuplicateEmailException{
		return new ResponseEntity<Customer>(customerService.isCustomerEmailExists(email),HttpStatus.OK); 	
	}
	
	@GetMapping("/mobno/{mobileNumber}")
	public ResponseEntity<Customer> forgetPassword(@PathVariable("mobileNumber") String mobileNumber)throws IdNotFoundException{
		return new ResponseEntity<Customer>(customerService.getCustomerByMobileNumber(mobileNumber),HttpStatus.OK);
		 }
	
	@PostMapping
	public ResponseEntity<String> addCustomer(@RequestBody CustomerDto customerDto) {
		return new ResponseEntity<String>(customerService.addCustomer(customerDto), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> updateCustomer(@RequestBody CustomerDto customerDto) throws IdNotFoundException{
		return	new ResponseEntity<>(customerService.updateCustomer(customerDto), new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/deletecus/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long id)throws IdNotFoundException{	
		return	new ResponseEntity<>(customerService.deleteCustomer(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomer() throws NullPointerException {	 
		 return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK); 
	}
	
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> userNotFound(NullPointerException e) {
		return new ResponseEntity<>("No Customer Data found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<String> duplicateIdFound(DuplicateEmailException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
