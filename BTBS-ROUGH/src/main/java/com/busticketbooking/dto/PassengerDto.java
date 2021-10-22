package com.busticketbooking.dto;

import javax.validation.constraints.NotNull;

import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;

import lombok.Data;

@Data
public class PassengerDto {

	@NotNull(message = "Name should not be empty")
	private String name;
	private int id;
	private String gender;
	private int age;

	@NotNull(message = "Seat number should not be empty")
	private int seatNumber;
	private Customer customer;
	private Bus bus;

}
