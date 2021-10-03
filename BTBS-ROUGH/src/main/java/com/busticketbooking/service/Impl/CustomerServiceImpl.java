package com.busticketbooking.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busticketbooking.controller.MailSend;
import com.busticketbooking.dao.CustomerDao;
import com.busticketbooking.dto.CustomerDto;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.DatabaseException;
import com.busticketbooking.exception.DuplicateEmailException;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.service.CustomerService;
import com.busticketbooking.util.mapper.CustomerMapper;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerDao customerDao;
	@Override
	public boolean isCustomerExists(Long id) {
try {
        if(customerDao.isCustomerExists(id))
		return customerDao.isCustomerExists(id);
    	else
			throw new BusinessLogicException("Customer  Id : " + id + " Not found");
		
	}catch(DatabaseException e) {
		throw new BusinessLogicException(e.getMessage());
	}
	}

	@Override
	public List<Customer> getAllCustomer() {
		try {
		if((customerDao.getAllCustomer()).size()==0)
			throw new BusinessLogicException("No Customer Data available");
		return customerDao.getAllCustomer();
	}catch(DatabaseException e) {
		throw new BusinessLogicException(e.getMessage());
	}
		}

	@Override
	public String deleteCustomer(Long id) {
		try {
		 if(customerDao.isCustomerExists(id)) 
		return customerDao.deleteCustomer(id);
				throw new BusinessLogicException("Customer with Customer Id:"+id+" Not Found!");
	}catch(DatabaseException e) {
		throw new BusinessLogicException(e.getMessage());
	}
		}

	@Override
	public String addCustomer(CustomerDto dto) {
		 try {
		  Customer customer = CustomerMapper.dtoToEntity(dto);
	
		   return customerDao.addCustomer(customer);
		 
		  
			
	}catch(DatabaseException e) {
		throw new BusinessLogicException(e.getMessage());
	}
		}

	@Override
	public String updateCustomer(CustomerDto dto) {
try {
		  Customer customer = CustomerMapper.dtoToEntity(dto);
		  long id = customer.getId();
		  if(customerDao.isCustomerExists(id))
			  return customerDao.updateCustomer(customer);
					throw new BusinessLogicException("Customer with Customer Id:"+id+" Not Found!");
		 
	}catch(DatabaseException e) {
		throw new BusinessLogicException(e.getMessage());
	}
		}

	@Override
	public Customer isCustomerEmailExists(String email) {
		 	 
		 
			 return customerDao.isCustomerEmailExists(email);
		 
	}

	@Override
	public Customer getCustomerByMobileNumber(String mobileNumber) {
		
		return customerDao.getCustomerByMobileNumber(mobileNumber);
	}

	@Override
	public Customer getCustomerById(Long id) {
		try {
		if(customerDao.isCustomerExists(id))
		return customerDao.getCustomerById(id);
	
			throw new BusinessLogicException("Customer with Customer Id:"+id+" Not Found!");
}catch(DatabaseException e) {
throw new BusinessLogicException(e.getMessage());
}
	}

	@Override
	public Customer getCustomerByEmailAndPassword(String email, String password) {
		
		return customerDao.getCustomerByEmailAndPassword(email, password);
	}

	@Override
	public Customer forgetPassword(String email) {
		try {
		Customer customer =customerDao.forgetPassword(email);
		if(customer != null) {
			 String message = "Mrs./Mr. " + customer.getName() +
					  ", \n Your have requested password "+
					  "\n Account Details:- "+
					  "\n  Name: "
					  + customer.getName() +
					  "\n Password  : " + customer.getPassword()+

					  "\n Mobile Number  : " + customer.getMobileNumber();
					  
					  
					 
			  MailSend.sendMail(email, "Account Created Successfully", message);
			 return customer;
		}
		
	else {
	return	null;}
	} catch(DatabaseException e) {
 throw new BusinessLogicException(e.getMessage());
 }
 	}
}