package com.busticketbooking.service;

import java.util.List;

import com.busticketbooking.dto.CustomerDto;
import com.busticketbooking.entity.Customer;

public interface CustomerService {

	/**
	 * check if customer exists
	 * 
	 * @param id
	 * @return
	 */
	boolean isCustomerExists(Long id);

	/**
	 * get all customers
	 * 
	 * @return
	 */
	List<Customer> getAllCustomer();

	/**
	 * delete customer by id
	 * 
	 * @param id
	 * @return
	 */
	String deleteCustomer(Long id);

	/**
	 * add customer
	 * 
	 * @param dto
	 * @return
	 */
	String addCustomer(CustomerDto dto);

	/**
	 * update customer
	 * 
	 * @param dto
	 * @return
	 */
	String updateCustomer(CustomerDto dto);

	/**
	 * check if customer email exists
	 * 
	 * @param email
	 * @return
	 */
	Customer isCustomerEmailExists(String email);

	/**
	 * getting customer by email and password for login
	 * 
	 * @param email
	 * @return
	 */
	Customer getCustomerByEmailAndPassword(String email, String password);

	/**
	 * get customer by id
	 * 
	 * @param id
	 * @return
	 */
	Customer getCustomerById(Long id);

	/**
	 * getting customer by email
	 * 
	 * @param email
	 * @return
	 */
	Customer forgetPassword(String email);
	
}
