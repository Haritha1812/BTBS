package com.busticketbooking.dao;

import java.util.List;

import com.busticketbooking.entity.BookTicket;
import com.busticketbooking.entity.Bus;

public interface BookTicketDao {
	public BookTicket getTicketById(Long id);
	
	public boolean isTicketIdExists(Long id);
	
	public List<BookTicket> getAllTickets();
	
	public String deleteTicket(Long id);
	
	public String addTicket(BookTicket bookTicket);

	public List<Bus> getTicketByBusId(long busId);
	
	public Bus getTicketByCustomerId(long customerId);
}
