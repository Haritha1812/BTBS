package com.busticketbooking.service;

import java.util.Date;
import java.util.List;

import com.busticketbooking.dto.BookTicketDto;
import com.busticketbooking.entity.BookTicket;
import com.busticketbooking.entity.Bus;

public interface BookTicketService {

	public BookTicket getTicketById(Long id);
	
	public boolean isTicketIdExists(Long id);
	
	
	public List<BookTicket> getAllTickets();
	
	public String deleteTicket(Long id);
	
	public String addTicket(BookTicketDto bookTicketDto);
	
	public String updateBookingStatus(long id,long customerId,long busId);
	
	public List<BookTicket> getTicketByCusId(long id);
	
	public Bus getTicketByCustomerId(long customerId);
	
	
}
