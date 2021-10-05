package com.busticketbooking.dao;

import com.busticketbooking.entity.Customer;

public interface LoginDao {

	/**
	 * getting customer by email and password for login
	 * 
	 * @param email
	 * @return
	 */
	Customer getCustomerByEmailAndPassword(String email, String password);

}
