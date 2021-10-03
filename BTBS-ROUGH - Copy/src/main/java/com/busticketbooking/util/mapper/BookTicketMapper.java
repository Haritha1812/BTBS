package com.busticketbooking.util.mapper;

import com.busticketbooking.dto.BookTicketDto;
import com.busticketbooking.entity.BookTicket;

public class BookTicketMapper {

	private BookTicketMapper() {
		
	}
	
	public static BookTicket dtoToEntity(BookTicketDto bt) {
		
	BookTicket bookTicket = new BookTicket();
			bookTicket.setId(bt.getId());
	    bookTicket.setBookingDate(bt.getBookingDate());
	    bookTicket.setBookingStatus(bt.getBookingStatus());
	   bookTicket.setBillAmount(bt.getBillAmount());
	   bookTicket.setBus(bt.getBus());
	   bookTicket.setCustomer(bt.getCustomer());
	   bookTicket.setNumberOfTickets(bt.getNumberOfTickets());
	   return bookTicket;
		
	}
}
