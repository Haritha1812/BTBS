package com.busticketbooking.service;

import java.util.List;

import com.busticketbooking.dto.CustomerDto;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.DuplicateEmailException;
import com.busticketbooking.exception.IdNotFoundException;

public interface CustomerService {


	public boolean isCustomerExists(Long id);
	
	public List<Customer> getAllCustomer() throws BusinessLogicException;

	public String deleteCustomer(Long id)throws BusinessLogicException;
	
	public  String addCustomer(CustomerDto dto);
	
	public String updateCustomer(CustomerDto dto)throws BusinessLogicException;
	
	public Customer isCustomerEmailExists(String email);
	
	public Customer getCustomerByMobileNumber(String mobileNumber);
	public Customer getCustomerByEmailAndPassword(String email,String password);

	public Customer getCustomerById(Long id)throws BusinessLogicException;
	
	public Customer forgetPassword(String email);
}
