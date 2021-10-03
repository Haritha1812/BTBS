package com.busticketbooking.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busticketbooking.controller.MailSend;
import com.busticketbooking.dao.BookTicketDao;
import com.busticketbooking.dao.BusDao;
import com.busticketbooking.dao.CustomerDao;
import com.busticketbooking.dto.BookTicketDto;
import com.busticketbooking.entity.BookTicket;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.exception.BusinessLogicException;
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
			throw new BusinessLogicException("Ticket with ticket Id : " + id + " Not found");
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
		String email = customer.getEmail();
		String name = customer.getName();
		 BookTicket bookTicket = bookTicketDao.getTicketById(id);
		  String message = "Mrs./Mr. " + name +
		  ", \n Your Booking for HMS Travels on "+bus.getDate()+"is approved " +
		  "\n Booking Details:- "+
		  "\n Booking Id: "
		  + bookTicket.getId() +
		  "\n Bus Name  : " + bus.getName()+

		  "\n Bus Type  : " + bus.getBusType()+
		  "\n Arrival Station  : " + bus.getRoute().getFromLocation()+
		  "\n Departure Station  : " + bus.getRoute().getToLocation()+
		  "\n Arrival time  : " + bus.getArrivalTime()+

		  "\n Departure time  : " + bus.getDepartureTime()+
		  "\n Number Of Tickets " +bookTicket.getNumberOfTickets() +
		  "\n Total Bill Amount: " +bookTicket.getBillAmount()+
		  "\n For more details download invoice from your account"+
		  "\n Thanks for your Booking.Continue your journey with us"
		 ;
		  System.out.println(email);
		  
		  MailSend.sendMail(email, "Account Created Successfully", message);
		return bookTicketDao.updateBookingStatus(id,bus, customer);
	}

}
