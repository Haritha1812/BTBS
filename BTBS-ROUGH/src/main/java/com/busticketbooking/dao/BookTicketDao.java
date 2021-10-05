package com.busticketbooking.dao;

import java.util.List;

import com.busticketbooking.entity.BookTicket;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;

public interface BookTicketDao {
	/**
	 * get ticket by id
	 * 
	 * @param id
	 * @return
	 */
	BookTicket getTicketById(Long id);

	/**
	 * check ticket exists
	 * 
	 * @param id
	 * @return
	 */
	boolean isTicketIdExists(Long id);

	/**
	 * get all tickets *
	 * 
	 * @return
	 */
	List<BookTicket> getAllTickets();

	/**
	 * delete ticket by id
	 * 
	 * @param id
	 * @return
	 */
	String deleteTicket(Long id);

	/**
	 * add ticket
	 * 
	 * @param bookTicket
	 * @return
	 */
	String addTicket(BookTicket bookTicket);

	/**
	 * get ticket by customer id
	 * 
	 * @param customer
	 * @return
	 */
	List<BookTicket> getTicketByCusId(Customer customer);

	/**
	 * get ticket by customer id
	 * 
	 * @param customerId
	 * @return
	 */
	Bus getTicketByCustomerId(long customerId);

	/**
	 * update booking status by bus,customer and booking id
	 * 
	 * @param id
	 * @param bus
	 * @param customer
	 * @return
	 */
	String updateBookingStatus(long id, Bus bus, Customer customer);
}
