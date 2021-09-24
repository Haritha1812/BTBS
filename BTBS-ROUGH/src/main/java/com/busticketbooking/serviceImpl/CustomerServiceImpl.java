package com.busticketbooking.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busticketbooking.dao.CustomerDao;
import com.busticketbooking.dto.CustomerDto;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.exception.DuplicateEmailException;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.service.CustomerService;
import com.busticketbooking.util.CustomerMapper;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerDao customerDao;
	@Override
	public boolean isCustomerExists(Long id) {
		// TODO Auto-generated method stub
		return customerDao.isCustomerExists(id);
	}

	@Override
	public List<Customer> getAllCustomer() {
		if((customerDao.getAllCustomer()).size()==0)
			throw new NullPointerException("No Customer Data available");
		return customerDao.getAllCustomer();
	}

	@Override
	public String deleteCustomer(Long id) {
		 if(customerDao.isCustomerExists(id)) 
		return customerDao.deleteCustomer(id);
				throw new IdNotFoundException("Customer with Customer Id:"+id+" Not Found!");
	}

	@Override
	public String addCustomer(CustomerDto dto) {
		 
		  Customer customer = CustomerMapper.dtoToEntity(dto);
		  long id = customer.getId();
		  if(customerDao.isCustomerExists(id)) return customerDao.addCustomer(customer);
					throw new IdNotFoundException("Customer with Customer Id:"+id+" Not Found!");
		 
		  
			
	}

	@Override
	public String updateCustomer(CustomerDto dto) {

		  Customer customer = CustomerMapper.dtoToEntity(dto);
		  long id = customer.getId();
		  if(customerDao.isCustomerExists(id))
			  return customerDao.updateCustomer(customer);
					throw new IdNotFoundException("Customer with Customer Id:"+id+" Not Found!");
		 
	}

	@Override
	public Customer isCustomerEmailExists(String email) {

		 if((customerDao.isCustomerEmailExists(email))==null)
			 throw new DuplicateEmailException("Customer with email :"+email+" not exists");
		 
			 return customerDao.isCustomerEmailExists(email);
		 
	}

	@Override
	public Customer getCustomerByMobileNumber(String mobileNumber) {
		if(customerDao.getCustomerByMobileNumber(mobileNumber)== null)
			throw new IdNotFoundException("Customer with mobilenumber :"+mobileNumber+" not exists");
		
		return customerDao.getCustomerByMobileNumber(mobileNumber);
	}

	@Override
	public Customer getCustomerById(Long id) {
		if(customerDao.isCustomerExists(id))
		return customerDao.getCustomerById(id);
	
			throw new IdNotFoundException("Customer with Customer Id:"+id+" Not Found!");
}
}