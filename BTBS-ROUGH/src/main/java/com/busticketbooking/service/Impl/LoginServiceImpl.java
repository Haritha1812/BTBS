package com.busticketbooking.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.busticketbooking.dao.CustomerDao;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.service.LoginService;

public class LoginServiceImpl implements LoginService{
	@Autowired
	CustomerDao customerDao;

	
	@Override
	public Customer getCustomerByEmailAndPassword(String email, String password) {

//
//		Customer customer = customerDao.getCustomerByEmailAndPassword(email, password);
//		System.out.println(customer);
		if (customerDao.getCustomerByEmailAndPassword(email, password) != null ) {

			return customerDao.getCustomerByEmailAndPassword(email, password);
		} else

			throw new BusinessLogicException("Customer with Customer email and password Not Found!");

	}
}
