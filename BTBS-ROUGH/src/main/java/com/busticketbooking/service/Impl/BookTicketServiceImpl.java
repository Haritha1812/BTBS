package com.busticketbooking.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busticketbooking.dao.BookTicketDao;
import com.busticketbooking.dao.BusDao;
import com.busticketbooking.dao.CustomerDao;
import com.busticketbooking.dto.BookTicketDto;
import com.busticketbooking.entity.BookTicket;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.service.BookTicketService;
import com.busticketbooking.util.mapper.BookTicketMapper;

@Service
public class BookTicketServiceImpl implements BookTicketService {

	@Autowired
	private BusDao busDao;

	@Autowired
	private BookTicketDao bookTicketDao;
	

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public BookTicket getTicketById(Long id) {
		if (bookTicketDao.isTicketIdExists(id)) {
			return bookTicketDao.getTicketById(id);
		} else {
			throw new IdNotFoundException("Ticket with ticket Id : " + id + " Not found");
		}
	}

	@Override
	public boolean isTicketIdExists(Long id) {
		return  bookTicketDao.isTicketIdExists(id);
	}

	@Override
	public List<BookTicket> getAllTickets() {
		return bookTicketDao.getAllTickets();
	}

	@Override
	public String deleteTicket(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addTicket(BookTicketDto bookTicketDto) {
		BookTicket bookTicket = BookTicketMapper.dtoToEntity(bookTicketDto);
		Customer customer = customerDao.getCustomerById(bookTicket.getCustomer().getId());
		Bus bus = busDao.getBusById(bookTicket.getBus().getId());
		bookTicket.setBus(bus);
		bookTicket.setCustomer(customer);
		return bookTicketDao.addTicket(bookTicket);
	}

	@Override
	public List<BookTicket> getTicketByCusId(long id) {
		Customer customer = customerDao.getCustomerById(id);
		return bookTicketDao.getTicketByCusId(customer);
	}

	@Override
	public Bus getTicketByCustomerId(long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateBookingStatus(long id,long busId,long customerId ) {
		Customer customer = customerDao.getCustomerById(customerId);
		Bus bus =  busDao.getBusById(busId);
		return bookTicketDao.updateBookingStatus(id,bus, customer);
	}

}
