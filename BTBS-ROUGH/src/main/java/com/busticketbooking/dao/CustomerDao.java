package com.busticketbooking.dao;

import java.util.List;

import com.busticketbooking.entity.Customer;

public interface CustomerDao {

	
public boolean isCustomerExists(Long id);
	
	public List<Customer> getAllCustomer();

	public String deleteCustomer(Long id);
	
	public  String addCustomer(Customer Customer);
	
	public String updateCustomer(Customer Customer);

	public Customer isCustomerEmailExists(String email);
	
	public Customer getCustomerByMobileNumber(String mobileNumber);
	
	public Customer getCustomerById(Long id);

	public Customer getCustomerByEmailAndPassword(String email,String password);
	public Customer forgetPassword(String email);
}
