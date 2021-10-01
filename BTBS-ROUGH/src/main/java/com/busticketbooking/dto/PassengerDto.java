package com.busticketbooking.dto;

import javax.persistence.Id;

import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;

import lombok.Data;

@Data
public class PassengerDto {

	private String name;
	private int id;
	private String gender;
	private int age;
	private String seatNumber;
	private Customer customer;
	private Bus bus;
	
}
