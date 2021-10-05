package com.busticketbooking.service;

import com.busticketbooking.entity.Customer;

public interface LoginService {

	/**
	 * getting customer by email and password for login
	 * 
	 * @param email
	 * @return
	 */
	Customer getCustomerByEmailAndPassword(String email, String password);
}
