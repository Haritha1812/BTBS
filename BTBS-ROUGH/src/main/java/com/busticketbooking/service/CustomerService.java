package com.busticketbooking.service;

import java.util.List;

import com.busticketbooking.dto.CustomerDto;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.exception.DuplicateEmailException;
import com.busticketbooking.exception.IdNotFoundException;

public interface CustomerService {


	public boolean isCustomerExists(Long id);
	
	public List<Customer> getAllCustomer() throws NullPointerException;

	public String deleteCustomer(Long id)throws IdNotFoundException;
	
	public  String addCustomer(CustomerDto dto);
	
	public String updateCustomer(CustomerDto dto)throws IdNotFoundException;
	
	public Customer isCustomerEmailExists(String email) throws DuplicateEmailException;
	
	public Customer getCustomerByMobileNumber(String mobileNumber)throws IdNotFoundException;

	public Customer getCustomerById(Long id)throws IdNotFoundException;
}
