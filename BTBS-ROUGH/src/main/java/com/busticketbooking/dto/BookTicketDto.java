package com.busticketbooking.dto;

import java.util.Date;

import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTicketDto {

	private long id;
	private long billAmount;
	private String bookingStatus;
	private Date bookingDate;
	private long numberOfTickets;
	private Bus bus;
	private Customer customer;

}