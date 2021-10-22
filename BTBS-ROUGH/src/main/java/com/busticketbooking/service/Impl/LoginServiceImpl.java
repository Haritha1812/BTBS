package com.busticketbooking.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.busticketbooking.dao.CustomerDao;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	CustomerDao customerDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Customer getCustomerByEmailAndPassword(String email, String password) {


		Customer customer = customerDao.getCustomerByEmailAndPassword(email, password);
		

		if (passwordEncoder.matches(password, customer.getPassword())) {
			return customerDao.getCustomerByEmailAndPassword(email, password);
		} else

			throw new BusinessLogicException("Customer with Customer email and password Not Found!");

	}
}
