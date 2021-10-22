package com.busticketbooking.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busticketbooking.controller.MailSend;
import com.busticketbooking.dao.BookTicketDao;
import com.busticketbooking.dao.BusDao;
import com.busticketbooking.dao.CustomerDao;
import com.busticketbooking.dao.PassengerDao;
import com.busticketbooking.dao.SeatDao;
import com.busticketbooking.dto.BookTicketDto;
import com.busticketbooking.entity.BookTicket;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.entity.Passenger;
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
	
	@Autowired
	private PassengerDao passengerDao;
	

	@Autowired
	private SeatDao seatDao;

	private Customer customer;

	private Bus bus;

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
			logger.error("error in getting ticket by id");
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
			if (bookTicketDao.getAllTickets().isEmpty()) {

				throw new BusinessLogicException("Tickets not found");
			}
			return bookTicketDao.getAllTickets();

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public String addTicket(BookTicketDto bookTicketDto) {

		logger.info("Entering Add Tickets function in service layer");
		try {

			if (bookTicketDto == null || bookTicketDto.getCustomer() == null || bookTicketDto.getBus() == null) {
				throw new BusinessLogicException("No Required data found");
			}
			BookTicket bookTicket = BookTicketMapper.dtoToEntity(bookTicketDto);
			Customer customer = customerDao.getCustomerById(bookTicket.getCustomer().getId());
			if (customer == null) {
				throw new BusinessLogicException("No Customer data found");
			}
			Bus bus = busDao.getBusById(bookTicket.getBus().getId());
			if (bus == null) {
				throw new BusinessLogicException("No Bus data found");
			}

			bookTicket.setBus(bus);
			bookTicket.setCustomer(customer);
			return bookTicketDao.addTicket(bookTicket);

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<BookTicket> getTicketByCusId(long id) {

		logger.info("Entering Get Tickets by customer id function in service layer");
		try {
			Customer customer = customerDao.getCustomerById(id);
			if (customer == null)
			{	
				throw new BusinessLogicException("Customer data not found");
			}
			if(bookTicketDao.getTicketByCusId(customer).isEmpty())
			{

				throw new BusinessLogicException("Tickets with customer id" + id + " not found");
			}
			return bookTicketDao.getTicketByCusId(customer);
			
		} catch (DatabaseException e) {

			logger.error("Error in getting ticket by customer id");
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateBookingStatus(long id, long busId, long customerId) {

		logger.info("Entering Update Tickets function in service layer");
		try {
			BookTicket bookTicket = bookTicketDao.getTicketById(id);
			if (bookTicket != null) {
				Customer customer = customerDao.getCustomerById(customerId);
				if (customer != null) {
					Bus bus = busDao.getBusById(busId);
					if (bus != null) {
						String email = customer.getEmail();
						String name = customer.getName();

						// use string format
						String message = "Mrs./Mr. " + name + ", \n Your Booking for HMS Travels on " + bus.getDate()
								+ "is approved " + "\n Booking Details:- " + "\n Booking Id: " + bookTicket.getId()
								+ "\n Bus Name  : " + bus.getName() +

								"\n Bus Type  : " + bus.getBusType() + "\n Arrival Station  : "
								+ bus.getRoute().getFromLocation() + "\n Departure Station  : "
								+ bus.getRoute().getToLocation() + "\n Arrival time  : " + bus.getArrivalTime() +

								"\n Departure time  : " + bus.getDepartureTime() + "\n Number Of Tickets "
								+ bookTicket.getNumberOfTickets() + "\n Total Bill Amount: "
								+ bookTicket.getBillAmount() + "\n For more details download invoice from your account"
								+ "\n Thanks for your Booking.Continue your journey with us";

						MailSend.sendMail(email, "Booking Details", message);
						return bookTicketDao.updateBookingStatus(id, bus, customer);
					} else {
						throw new BusinessLogicException("Bus data not found for id " + busId);
					}
				} else {
					throw new BusinessLogicException("Customer data not found for id " + customerId);
				}
			} else {
				throw new BusinessLogicException("No ticket data not found for id " + id);
			}

		} catch (DatabaseException e) {

			logger.error("Error in update ticket for id" + id);
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String deleteBookTicket(Long id) {

	
	logger.info("Entering delete Tickets function in service layer");
	try {
		BookTicket bookTicket = bookTicketDao.getTicketById(id);
		if (bookTicket == null) {

			throw new BusinessLogicException("No ticket data not found for id " + id);
		}


		customer = customerDao.getCustomerById(bookTicket.getCustomer().getId());
		if (customer == null) {
			throw new BusinessLogicException("Customer data not found for id " + customer.getId());
		}
		bus = busDao.getBusById(bookTicket.getBus().getId());
	
		if (bus == null) {
			throw new BusinessLogicException("Bus data not found for id " + bus.getId());
		}
		String email = customer.getEmail();
		String name = customer.getName();

		// use string format
		String message = "Mrs./Mr. " + name + ", \n Your Booking for HMS Travels on " + bus.getDate()
				+ "is rejected due to some unavoidable reasons"+"\n Contact admin for further details"
				;

		MailSend.sendMail(email, "Booking Details", message);
		//to delete the passenger 
		List<Passenger> passenger = passengerDao.getPassengerByBusIdAndCusId(bookTicket.getBus(),bookTicket.getCustomer());
		for(int i=0;i<passenger.size();i++) {


			logger.error("Entering updateseat status for rejecting the booking details for passenger"+passenger.get(i).getName());
			int seatNumber = passenger.get(i).getSeatNumber();
			Bus buses=passenger.get(i).getBus();
			seatDao.update(seatNumber, buses);
			
		}
		
		for(int i=0;i<passenger.size();i++) {


			logger.info("Entering deleting passenger details for booking id"+bookTicket.getId()+"for passenger"+passenger.get(i).getId());
			long passengerId =passenger.get(i).getId();
			passengerDao.deletePassenger(passengerId);
			
			
		}
		return bookTicketDao.deleteBooking(id);
		
		
	}catch (DatabaseException e) {

		logger.error("Error in delete ticket for id" + id);
		throw new BusinessLogicException(e.getMessage());
	}
	}

	@Override
	public List<BookTicket> getTicketByBusId(long id) {

		logger.info("Entering Get Tickets by Bus id function in service layer");
		try {
			Bus bus = busDao.getBusById(id);
			logger.info(bus);
			if (bus == null)
			{	
				throw new BusinessLogicException("Bus data not found");
			}
			if(bookTicketDao.getTicketByBusId(bus).isEmpty())
			{

				throw new BusinessLogicException("Tickets with Bus id" + id + " not found");
			}
			return bookTicketDao.getTicketByBusId(bus);
			
		} catch (DatabaseException e) {

			logger.error("Error in getting ticket by customer id");
			throw new BusinessLogicException(e.getMessage());
		}
	}
	}
	

