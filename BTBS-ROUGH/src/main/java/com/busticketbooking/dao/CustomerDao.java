package com.busticketbooking.dao;

import java.util.List;

import com.busticketbooking.entity.Customer;

public interface CustomerDao {

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
	 * @param Customer
	 * @return
	 */
	String addCustomer(Customer Customer);

	/**
	 * update customer
	 * 
	 * @param Customer
	 * @return
	 */
	String updateCustomer(Customer Customer);

	/**
	 * check if customer email exists
	 * 
	 * @param email
	 * @return
	 */
	Customer isCustomerEmailExists(String email);


	/**
	 * get customer by id
	 * 
	 * @param id
	 * @return
	 */
	Customer getCustomerById(Long id);

	/**
	 * getting customer by email and password for login
	 * 
	 * @param email
	 * @return
	 */
	Customer getCustomerByEmailAndPassword(String email, String password);

	/**
	 * getting customer by email
	 * 
	 * @param email
	 * @return
	 */
	Customer forgetPassword(String email);
}
