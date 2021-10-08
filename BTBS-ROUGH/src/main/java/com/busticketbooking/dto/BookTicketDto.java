package com.busticketbooking.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

    @Min(value =0, message = "Bill amount should be positive")
	private long billAmount;

	@NotNull(message="Booking Status should not be empty")
	private String bookingStatus;

	@NotNull(message="Booking date should not be empty")
	private Date bookingDate;

    @Min(value =0, message = "Number of tickets should be positive")
	private long numberOfTickets;
	
	private Bus bus;
	private Customer customer;
	public BookTicketDto(long id, long billAmount, String bookingStatus, Date bookingDate, long numberOfTickets) {
		super();
		this.id = id;
		this.billAmount = billAmount;
		this.bookingStatus = bookingStatus;
		this.bookingDate = bookingDate;
		this.numberOfTickets = numberOfTickets;
	}

}