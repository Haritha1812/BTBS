package com.busticketbooking.service;

import java.util.List;

import com.busticketbooking.dto.CustomerDto;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.DuplicateEmailException;
import com.busticketbooking.exception.IdNotFoundException;

public interface CustomerService {

	/**
	 * check if customer exists
	 * @param id
	 * @return
	 */
	public boolean isCustomerExists(Long id);
	
    /**
     * get all customers
     * @return
     */
	public List<Customer> getAllCustomer() ;

	/**
	 * delete customer by id
	 * @param id
	 * @return
	 */
	public String deleteCustomer(Long id);
	/**
	 * add customer
	 * @param dto
	 * @return
	 */
	public  String addCustomer(CustomerDto dto);
	/**
	 * update customer
	 * @param dto
	 * @return
	 */
	public String updateCustomer(CustomerDto dto);
	/**
	 * check if customer email exists
	 * @param email
	 * @return
	 */
	public Customer isCustomerEmailExists(String email);

	/**
	 * get customer by mobile number
	 * @param mobileNumber
	 * @return
	 */
	public Customer getCustomerByMobileNumber(String mobileNumber);
	/**
	 * getting customer by email and password for login
	 * @param email
	 * @return
	 */
	public Customer getCustomerByEmailAndPassword(String email,String password);
	/**
	 * get customer by id
	 * @param id
	 * @return
	 */
	public Customer getCustomerById(Long id);
	/**
	 * getting customer by email
	 * @param email
	 * @return
	 */
	public Customer forgetPassword(String email);
}
