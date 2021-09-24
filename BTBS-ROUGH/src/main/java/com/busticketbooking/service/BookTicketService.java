package com.busticketbooking.service;

import java.util.Date;
import java.util.List;

import com.busticketbooking.entity.BookTicket;
import com.busticketbooking.entity.Bus;

public interface BookTicketService {

	public BookTicket getTicketById(Long id);
	
	public boolean isTicketIdExists(Long id);
	
	public boolean isSeatNoExists(long seatNo);
	
	public List<BookTicket> getAllTickets();
	
	public String deleteTicket(Long id);
	
	public String addTicket(Long id);
	
	public Bus getTicketByBusId(long busId);
	
	public Bus getTicketByCustomerId(long customerId);
}
