package com.busticketbooking.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busticketbooking.entity.Customer;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.response.HttpResponseStatus;
import com.busticketbooking.service.CustomerService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("login")
public class LoginController {
	@Autowired
	CustomerService customerService;


	private static final Logger logger = LogManager.getLogger(LoginController.class.getName());
	
	/**
	 * getting customer by email and password for login function
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	@GetMapping("/{email}/{password}")
	public ResponseEntity<HttpResponseStatus> login(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		logger.info("Entering Customer Bus By email and password function");
		try {
			Customer customer = customerService.getCustomerByEmailAndPassword(email, password);
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.OK.value(), "Data retrieved successfully", customer),
					HttpStatus.OK);

		} catch (BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(
					new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

}
