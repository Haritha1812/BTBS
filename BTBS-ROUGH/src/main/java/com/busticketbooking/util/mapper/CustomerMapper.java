package com.busticketbooking.util.mapper;

import com.busticketbooking.dto.CustomerDto;
import com.busticketbooking.entity.Customer;

public class CustomerMapper {

	public static Customer dtoToEntity(CustomerDto d) {

		Customer customer = new Customer();
		customer.setId(d.getId());
		customer.setName(d.getName());
		customer.setEmail(d.getEmail());
		customer.setMobileNumber(d.getMobileNumber());
		customer.setPassword(d.getPassword());
		return customer;
	}

}
