package com.busticketbooking.dao;

import java.util.List;

import com.busticketbooking.entity.Customer;

public interface CustomerDao {

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
	public List<Customer> getAllCustomer();

	/**
	 * delete customer by id
	 * @param id
	 * @return
	 */
	public String deleteCustomer(Long id);
	
	/**
	 * add customer
	 * @param Customer
	 * @return
	 */
	public  String addCustomer(Customer Customer);
	
	/**
	 * update customer
	 * @param Customer
	 * @return
	 */
	public String updateCustomer(Customer Customer);

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
	 * get customer by id
	 * @param id
	 * @return
	 */
	public Customer getCustomerById(Long id);
	/**
	 * getting customer by email and password for login
	 * @param email
	 * @return
	 */
	public Customer getCustomerByEmailAndPassword(String email,String password);
	/**
	 * getting customer by email 
	 * @param email
	 * @return
	 */
	public Customer forgetPassword(String email);
}
