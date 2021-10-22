package com.busticketbooking.service.Impl;

import java.util.List;
import java.util.Random;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.busticketbooking.controller.MailSend;
import com.busticketbooking.dao.CustomerDao;
import com.busticketbooking.dto.CustomerDto;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.DatabaseException;
import com.busticketbooking.service.CustomerService;
import com.busticketbooking.util.mapper.CustomerMapper;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	CustomerDao customerDao;

	private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class.getName());
	@Override
	public boolean isCustomerExists(Long id) {
		try {

			logger.info("Entering Get customer by id function in service layer");

			if (customerDao.isCustomerExists(id))
				return customerDao.isCustomerExists(id);
			else
				throw new BusinessLogicException("Customer  Id : " + id + " Not found");

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Customer> getAllCustomer() {
		try {

			logger.info("Entering Get all customers function in service layer");

			if ((customerDao.getAllCustomer()).size() == 0)
				throw new BusinessLogicException("No Customer Data available");
			return customerDao.getAllCustomer();
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String deleteCustomer(Long id) {
		try {

			logger.info("Entering delete bus by id function in service layer");

			if (customerDao.isCustomerExists(id))
				return customerDao.deleteCustomer(id);
			throw new BusinessLogicException("Customer with Customer Id:" + id + " Not Found!");
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String addCustomer(CustomerDto dto) {
		try {

			logger.info("Entering add customer function in service layer");

			if (dto == null) {
				throw new BusinessLogicException("Customer data Not Found!");
			} 
				Customer customer = CustomerMapper.dtoToEntity(dto);
				customer.setPassword(passwordEncoder.encode(customer.getPassword()));
				return customerDao.addCustomer(customer);

			
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		} catch (ConstraintViolationException e) {

			throw new BusinessLogicException("Customer Email already exists!!");
		}
	}

	@Override
	public String updateCustomer(CustomerDto dto) {
		try {

			logger.info("Entering update customer function in service layer");
			if (dto == null) {

				throw new BusinessLogicException("Customer data Not Found!");
			}
			Customer customer = CustomerMapper.dtoToEntity(dto);
			customer.setPassword(passwordEncoder.encode(customer.getPassword()));
				long id = customer.getId();
				if (customerDao.isCustomerExists(id))
					return customerDao.updateCustomer(customer);
				throw new BusinessLogicException("Customer with Customer Id:" + id + " Not Found!");

			
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public Customer isCustomerEmailExists(String email) {
		try {

			logger.info("Entering  customer exists function in service layer");
			if (customerDao.isCustomerEmailExists(email) != null)
				return customerDao.isCustomerEmailExists(email);
			else

				throw new BusinessLogicException("Email not exists");
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public Customer getCustomerById(Long id) {
		try {

			logger.info("Entering get customer by id function in service layer");
			if (customerDao.isCustomerExists(id))
				return customerDao.getCustomerById(id);

			throw new BusinessLogicException("Customer with Customer Id:" + id + " Not Found!");
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public Customer getCustomerByEmailAndPassword(String email, String password) {

		logger.info("Entering get customer by email function in service layer");
		if (customerDao.getCustomerByEmailAndPassword(email, password) != null) {

			return customerDao.getCustomerByEmailAndPassword(email, password);
		} else

			throw new BusinessLogicException("Customer with Customer email and password Not Found!");

	}

	@Override
	public Customer forgetPassword(String email) {
		try {

			logger.info("Entering forget password function in service layer");
			if (isCustomerEmailExists(email)==null) {
				throw new BusinessLogicException("Customer with Customer email  Not Found!");
			}

			Customer customer = customerDao.isCustomerEmailExists(email);
	    	String password=	generatePassword();
				String message = "Mrs./Mr. " + customer.getName() + ", \n Your have requested password "
						+ "\n Account Details:- " + "\n  Name: " + customer.getName() + "\n Password  : "
						+ password +

						"\n Mobile Number  : " + customer.getMobileNumber() + "http://localhost:4200/home";

				MailSend.sendMail(email, "Password Credentials", message);

				password = passwordEncoder.encode(password);
				customer.setPassword(password);
				return customerDao.forgetPassword(customer);

	 
			
			
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}
	public static String generatePassword() {
		// It will generate 6 digit random Number.
		// from 0 to 999999
		Random rnd = new Random();
		int number = rnd.nextInt(999999);



		// this will convert any number sequence into 6 character.
		return String.format("%06d", number);



		}
}