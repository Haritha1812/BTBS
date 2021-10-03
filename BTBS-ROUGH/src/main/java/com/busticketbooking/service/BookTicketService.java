package com.busticketbooking.service;

import java.util.List;

import com.busticketbooking.dto.BookTicketDto;
import com.busticketbooking.entity.BookTicket;

public interface BookTicketService {

	/**
	 * get ticket by id
	 * @param id
	 * @return
	 */
	public BookTicket getTicketById(Long id) ;
	
	/**
	 * check ticket exists
	 * @param id
	 * @return
	 */
	public boolean isTicketIdExists(Long id);
	
	/**
	 * get all tickets 
	 * @return
	 */
	public List<BookTicket> getAllTickets();
	/**
	 * delete ticket by id
	 * @param id
	 * @return
	 */
	public String deleteTicket(Long id);

	/**
	 * add ticket
	 * @param bookTicketDto
	 * @return
	 */
	public String addTicket(BookTicketDto bookTicketDto);
	/**
	 * update booking status by bus,customer and booking id
	 * @param id
	 * @param busId
	 * @param customerId
	 * @return
	 */
	public String updateBookingStatus(long id,long customerId,long busId);
	/**
	 * get ticket by customer id
	 * @param id
	 * @return
	 */
	public List<BookTicket> getTicketByCusId(long id);
	
	
	
}
