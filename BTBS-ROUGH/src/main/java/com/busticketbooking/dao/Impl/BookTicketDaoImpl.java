package com.busticketbooking.dao.Impl;

import static com.busticketbooking.util.BusTicketBookingConstants.ERROR_IN_DELETE;
import static com.busticketbooking.util.BusTicketBookingConstants.ERROR_IN_FETCH;
import static com.busticketbooking.util.BusTicketBookingConstants.ERROR_IN_INSERT;
import static com.busticketbooking.util.BusTicketBookingConstants.ERROR_IN_UPDATE;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.busticketbooking.dao.BookTicketDao;
import com.busticketbooking.entity.BookTicket;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.entity.Passenger;
import com.busticketbooking.exception.DatabaseException;

@Repository
public class BookTicketDaoImpl implements BookTicketDao {
	String result = null;

	private static final Logger logger = LogManager.getLogger(BookTicketDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public BookTicket getTicketById(Long id) {
		logger.info("Entering Get Ticket By Id Function in dao for id" + id);
		try {
			Session session = sessionFactory.getCurrentSession();

			return session.get(BookTicket.class, id);
		} catch (Exception e) {
			// errorlog
			logger.error("Error while fetching details in get ticket by id function in dao for id" + id);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public boolean isTicketIdExists(Long id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			BookTicket bookTicket = session.get(BookTicket.class, id);
			return (bookTicket != null);
		} catch (Exception e) {

			logger.error("Error while fetching details is ticket id exists function in dao for id" + id);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<BookTicket> getAllTickets() {
		try {
			Session session = sessionFactory.getCurrentSession();

			Query<BookTicket> query = session.createQuery("From BookTicket");

			return (query.getResultList());
		} catch (Exception e) {

			logger.error("Error while fetching details in get tickets");
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Transactional
	@Override
	public String addTicket(BookTicket bookTicket) {

		logger.info("Entering Add Ticket Function");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(bookTicket);

			result = "Bus with bus number " + bookTicket.getId() + " added successfully";

			return result;
		} catch (Exception e) {

			logger.error("Error while  Adding ticket  function in dao ");
			throw new DatabaseException(ERROR_IN_INSERT);
		}
	}

	@Override
	public List<BookTicket> getTicketByCusId(Customer customer) {
		List<BookTicket> bookTicket = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "select i from BookTicket i WHERE i.customer=:cus";

			Query<BookTicket> query = session.createQuery(hql);
			query.setParameter("cus", customer);

			return (query.getResultList());
		} catch (Exception e) {

			logger.error("Error while fetching details in get ticket by customer id function in dao for id"
					+ customer.getId());
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Transactional
	@Override
	public String updateBookingStatus(long id, Bus bus, Customer customer) {

		logger.info("Entering Update Ticket Function in dao for id"+id);
		try {
			Session session = sessionFactory.getCurrentSession();
			BookTicket bookTicket = getTicketById(id);
			

			Query q = session.createQuery(
					"update BookTicket set bookingStatus=:status where bus=:bus AND customer=:customer AND id=:id");

			q.setParameter("id", id);
			q.setParameter("customer", customer);
			q.setParameter("bus", bus);
			q.setParameter("status", "Confirmed");
			int status = q.executeUpdate();

			return "Book ticket id "+id + " Updated successfully for bus Id: " + bus.getId();

		} catch (Exception e) {

			logger.error("Error while updating ticket by id function in dao for id"+id);
			throw new DatabaseException(ERROR_IN_UPDATE);
		}
	}
	@Override
	public String deleteBooking(long id) {
	
		try {
			Session session = sessionFactory.openSession();
		BookTicket bookTicket = session.get(BookTicket.class, id);
			if (bookTicket != null) {

				session.beginTransaction();
				session.delete(bookTicket);
				session.getTransaction().commit();
				result = "Deletion is successful for id: " + id;
			}
			return result;
		} catch (Exception e) {
			logger.error("Error while delete ticket by id function in dao for id" + id);
			throw new DatabaseException(ERROR_IN_DELETE);
		}
	
	}

	@Override
	public List<BookTicket> getTicketByBusId(Bus bus) {
		List<BookTicket> bookTicket = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "select i from BookTicket i WHERE i.bus=:bus";

			Query<BookTicket> query = session.createQuery(hql);
			query.setParameter("bus", bus);

			return (query.getResultList());
		} catch (Exception e) {

			logger.error("Error while fetching details in get ticket by Bus id function in dao for id"
					+ bus.getId());
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

}
