package com.busticketbooking.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busticketbooking.dto.CustomerDto;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.response.HttpResponseStatus;
import com.busticketbooking.service.CustomerService;
import com.busticketbooking.service.PassengerService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("Customer")
public class CustomerController {

	private static final Logger logger = LogManager.getLogger(CustomerController.class.getName());
	@Autowired
	CustomerService customerService;

	@Autowired
	PassengerService passengerService;

	String message;

	/**
	 * Get customer by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")

	public ResponseEntity<HttpResponseStatus> getid(@PathVariable("id") Long id) {
		logger.info("Entering Get Customer By Id function");
		try {
			Customer customer = customerService.getCustomerById(id);
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", customer),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * getting customer by id
	 * 
	 * @param email
	 * @return
	 */
	@GetMapping("/email/{email}")
	public ResponseEntity<HttpResponseStatus> getemail(@PathVariable("email") String email) {
		logger.info("Entering Get Customer By Email function");
		try {
			Customer customer = customerService.isCustomerEmailExists(email);
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", customer),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * getting customer by email for sending mail
	 * 
	 * @param email
	 * @return
	 */
	@GetMapping("/forget/{email}")
	public ResponseEntity<HttpResponseStatus> forgetpassword(@PathVariable("email") String email) {
		logger.info("Entering Get Customer By email function");
		try {
			Customer customer = customerService.forgetPassword(email);
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", customer),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	
	
	/**
	 * adding customer
	 * 
	 * @param customerDto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> add(@RequestBody CustomerDto customerDto) {
		logger.info("Entering Add Customer  function");
		try {
			message = customerService.addCustomer(customerDto);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * to update customer
	 * 
	 * @param customerDto
	 * @return
	 */
	@PutMapping
	public ResponseEntity<HttpResponseStatus> update(@RequestBody CustomerDto customerDto) throws IdNotFoundException {
		logger.info("Entering Update Customer function");
		try {
			customerService.updateCustomer(customerDto);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * delete customer by id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deletecus/{id}")
	public ResponseEntity<HttpResponseStatus> delete(@PathVariable Long id) {
		logger.info("Entering Delete Customer By id function");
		try {
			message = customerService.deleteCustomer(id);

			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(), message),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * get all customers
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getall() {
		logger.info("Entering Get Customers function");
		try {
			List<Customer> customer = customerService.getAllCustomer();
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", customer),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

}
