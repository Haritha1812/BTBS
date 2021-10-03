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
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.DatabaseException;
import com.busticketbooking.exception.DuplicateEmailException;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.exception.ResourceNotFoundException;
import com.busticketbooking.response.HttpResponseStatus;
import com.busticketbooking.service.CustomerService;
import com.busticketbooking.service.PassengerService;


@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("Customer")
public class CustomerController   {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	PassengerService passengerService;
	
	String message;
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getid(@PathVariable("id") Long id){
		
		 try {      Customer customer=   customerService.getCustomerById(id);
				return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",customer),HttpStatus.OK);

		 }
	catch(BusinessLogicException e) {
		return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
	}
	}
	@GetMapping("/email/{email}")
	public ResponseEntity<HttpResponseStatus> getemail(@PathVariable("email") String email){
		
		 try {      Customer customer=   customerService.isCustomerEmailExists(email);
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",customer),HttpStatus.OK);

	 }
catch(BusinessLogicException e) {
	return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
}
}
	
	@GetMapping("/forget/{email}")
	public ResponseEntity<HttpResponseStatus> forgetpassword(@PathVariable("email") String email){
	
		 try {      Customer customer=   customerService.forgetPassword(email);
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",customer),HttpStatus.OK);

	 }
catch(BusinessLogicException e) {
	return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
}
}
	
	@GetMapping("/mobno/{mobileNumber}")
	public ResponseEntity<HttpResponseStatus> forget(@PathVariable("mobileNumber") String mobileNumber){
		 try {      Customer customer=   customerService.getCustomerByMobileNumber(mobileNumber);
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",customer),HttpStatus.OK);

	 }
catch(BusinessLogicException e) {
	return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
}
}
	
	@GetMapping("/login/{email}/{password}")
	public ResponseEntity<HttpResponseStatus> login(@PathVariable("email") String email ,@PathVariable("password") String password){
	
		 try {      Customer customer=   customerService.getCustomerByEmailAndPassword(email,password);
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",customer),HttpStatus.OK);

	 }
catch(BusinessLogicException e) {
	return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
}
}
	
	@PostMapping
	public ResponseEntity<HttpResponseStatus> add(@RequestBody CustomerDto customerDto) {
	 try {    message= customerService.addCustomer(customerDto);
	
	return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),message),HttpStatus.OK);

} catch(BusinessLogicException e) {
	return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
}
}
	
	@PutMapping
	public ResponseEntity<HttpResponseStatus> update(@RequestBody CustomerDto customerDto) throws IdNotFoundException{
		 try {     customerService.updateCustomer(customerDto);
			
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),message),HttpStatus.OK);

		} catch(BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
		}
	@DeleteMapping("/deletecus/{id}")
	public ResponseEntity<HttpResponseStatus> delete(@PathVariable Long id)throws IdNotFoundException{	

		 try {    message= customerService.deleteCustomer(id);
			
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),message),HttpStatus.OK);

		} catch(BusinessLogicException e) {
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
		}
	
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getall() throws NullPointerException {	 
		 try {      List<Customer> customer=   customerService.getAllCustomer();
			return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.OK.value(),"Data retrieved successfully",customer),HttpStatus.OK);

	 }
catch(BusinessLogicException e) {
	return new ResponseEntity<HttpResponseStatus>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
}
}
	        // EXCEPTION HANDLER FOR BUSSINESSLOGICEXCEPTION.
			@ExceptionHandler(BusinessLogicException.class)
			public ResponseEntity<HttpResponseStatus> bussinessException (BusinessLogicException e) {
				return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value() ,e.getMessage()), HttpStatus.BAD_REQUEST);
			}
				
			// EXCEPTION HANDLER FOR DATABASEEXCEPTION.
			@ExceptionHandler(DatabaseException.class)
			public ResponseEntity<HttpResponseStatus> dataBaseException (DatabaseException e) {
				return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value() ,e.getMessage()), HttpStatus.BAD_REQUEST);
			}

}
