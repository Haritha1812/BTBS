package com.busticketbooking.dao;

import java.util.List;

import com.busticketbooking.entity.BookTicket;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;

public interface BookTicketDao {
	public BookTicket getTicketById(Long id);
	
	public boolean isTicketIdExists(Long id);
	
	public List<BookTicket> getAllTickets();
	
	public String deleteTicket(Long id);
	
	public String addTicket(BookTicket bookTicket);

	public List<BookTicket> getTicketByCusId(Customer customer);
	
	public Bus getTicketByCustomerId(long customerId);

	public String updateBookingStatus(long id,Bus bus,Customer customer);
}
