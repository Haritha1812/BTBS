package com.busticketbooking.service;

import java.util.List;

import com.busticketbooking.dto.BookTicketDto;
import com.busticketbooking.entity.BookTicket;

public interface BookTicketService {

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
	 * get all tickets
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
	 * @param bookTicketDto
	 * @return
	 */
	String addTicket(BookTicketDto bookTicketDto);

	/**
	 * update booking status by bus,customer and booking id
	 * 
	 * @param id
	 * @param busId
	 * @param customerId
	 * @return
	 */
	String updateBookingStatus(long id, long customerId, long busId);

	/**
	 * get ticket by customer id
	 * 
	 * @param id
	 * @return
	 */
	List<BookTicket> getTicketByCusId(long id);

}
