package com.busticketbooking.service.Impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.busticketbooking.exception.DatabaseException;
import com.busticketbooking.service.BookTicketService;
import com.busticketbooking.util.mapper.BookTicketMapper;

@Service
public class BookTicketServiceImpl implements BookTicketService {
	private static final Logger logger = LogManager.getLogger(BookTicketServiceImpl.class.getName());

	@Autowired
	private BusDao busDao;

	@Autowired
	private BookTicketDao bookTicketDao;

	@Autowired
	private CustomerDao customerDao;

	@Override
	public BookTicket getTicketById(Long id) {
		logger.info("Entering Get Ticket by id function in service layer");

		try {
			if (bookTicketDao.isTicketIdExists(id)) {
				return bookTicketDao.getTicketById(id);
			} else {
				throw new BusinessLogicException("Ticket with ticket Id : " + id + " Not found");
			}
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public boolean isTicketIdExists(Long id) {

		try {
			if (bookTicketDao.isTicketIdExists(id))
				return bookTicketDao.isTicketIdExists(id);
			else
				throw new BusinessLogicException("Ticket with ticket Id : " + id + " Not found");

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<BookTicket> getAllTickets() {
		logger.info("Entering Get Tickets function in service layer");

		try {
			if (bookTicketDao.getAllTickets() != null)

				return bookTicketDao.getAllTickets();
			else
				throw new BusinessLogicException("Tickets not found");

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}


	@Override
	public String addTicket(BookTicketDto bookTicketDto) {

		logger.info("Entering Add Tickets function in service layer");
		try {
			if(bookTicketDto!=null) {
			BookTicket bookTicket = BookTicketMapper.dtoToEntity(bookTicketDto);
			if(bookTicketDto.getCustomer()!=null) {
			Customer customer = customerDao.getCustomerById(bookTicket.getCustomer().getId());
			if(bookTicketDto.getBus()!=null ) {
			Bus bus = busDao.getBusById(bookTicket.getBus().getId());
			bookTicket.setBus(bus);
			bookTicket.setCustomer(customer);
			return bookTicketDao.addTicket(bookTicket);
			}else{

				throw new BusinessLogicException("No bus data found");
			}}else {
				throw new BusinessLogicException("No Customer data found");
			}}else {
				throw new BusinessLogicException("No bookticket data found");
			}
			
		
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<BookTicket> getTicketByCusId(long id) {

		logger.info("Entering Get Tickets by customer id function in service layer");
		try {
			Customer customer = customerDao.getCustomerById(id);
			if (customer != null)
				return bookTicketDao.getTicketByCusId(customer);
			else
				throw new BusinessLogicException("Tickets not found");

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateBookingStatus(long id, long busId, long customerId) {

		logger.info("Entering Update Tickets function in service layer");
		try {
			Customer customer = customerDao.getCustomerById(customerId);
			Bus bus = busDao.getBusById(busId);
			String email = customer.getEmail();
			String name = customer.getName();
			BookTicket bookTicket = bookTicketDao.getTicketById(id);
			String message = "Mrs./Mr. " + name + ", \n Your Booking for HMS Travels on " + bus.getDate()
					+ "is approved " + "\n Booking Details:- " + "\n Booking Id: " + bookTicket.getId()
					+ "\n Bus Name  : " + bus.getName() +

					"\n Bus Type  : " + bus.getBusType() + "\n Arrival Station  : " + bus.getRoute().getFromLocation()
					+ "\n Departure Station  : " + bus.getRoute().getToLocation() + "\n Arrival time  : "
					+ bus.getArrivalTime() +

					"\n Departure time  : " + bus.getDepartureTime() + "\n Number Of Tickets "
					+ bookTicket.getNumberOfTickets() + "\n Total Bill Amount: " + bookTicket.getBillAmount()
					+ "\n For more details download invoice from your account"
					+ "\n Thanks for your Booking.Continue your journey with us";
			System.out.println(email);

			MailSend.sendMail(email, "Account Created Successfully", message);
			return bookTicketDao.updateBookingStatus(id, bus, customer);
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

}
