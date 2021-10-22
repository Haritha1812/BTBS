package com.busticketbooking.dao.Impl;

import static com.busticketbooking.util.BusTicketBookingConstants.ERROR_IN_DELETE;
import static com.busticketbooking.util.BusTicketBookingConstants.ERROR_IN_FETCH;
import static com.busticketbooking.util.BusTicketBookingConstants.ERROR_IN_INSERT;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.busticketbooking.dao.PassengerDao;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.entity.Passenger;
import com.busticketbooking.exception.DatabaseException;

@Repository
public class PassengerDaoImpl implements PassengerDao {
	String result = null;

	private static final Logger logger = LogManager.getLogger(CustomerDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public String addPassenger(Passenger passenger) {
		logger.info("Entering Add Passenger Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(passenger);
			System.out.println(passenger);
			result = "Passenger with Passenger number " + passenger.getId() + " added successfully";

			return result;
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_INSERT);
		}
	}

	@Transactional
	@Override
	public boolean isPassengerExists(Long id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Passenger pass = session.get(Passenger.class, id);
			return (pass != null);
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Transactional
	@Override
	public List<Passenger> getAllPassenger() {
		try {
			Session session = sessionFactory.openSession();

			String hql = "FROM com.busticketbooking.entity.Passenger";
			Query<Passenger> query = session.createQuery(hql);

			return (query.getResultList().isEmpty() ? null : query.getResultList());
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public String deletePassenger(Long id) {
		try {

			logger.info("Entering delete Passenger Function"+id);
			
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Passenger passenger;
			passenger = session.get(Passenger.class, id);
			if (passenger != null) {
				session.delete(passenger);
				session.getTransaction().commit();
				//session.flush();
				result = "Deletion is successful for id: " + id;
			}
			
			return result;
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_DELETE);
		}

	}

	@Transactional
	@Override
	public List<Passenger> getPassengerByBusIdAndCusId(Bus bus, Customer customer) {
		try {
			Session session = sessionFactory.getCurrentSession();
		
			List<Passenger> resultList = session
					.createQuery("select i from Passenger i where i.bus=:id AND i.customer=:customer")
					.setParameter("id", bus).setParameter("customer", customer).getResultList();
			System.out.println(resultList);
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Transactional
	@Override
	public List<Passenger> getPassengerByCusId(Customer customer) {
		try {
			Session session = sessionFactory.getCurrentSession();
		
			List<Passenger> resultList = session.createQuery("select i from Passenger i where i.customer=:cus")
					.setParameter("cus", customer).getResultList();
		
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

}
