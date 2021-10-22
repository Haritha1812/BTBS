package com.busticketbooking.dao.Impl;

import static com.busticketbooking.util.BusTicketBookingConstants.ERROR_IN_FETCH;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.busticketbooking.dao.LoginDao;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.exception.DatabaseException;

@Repository
public class LoginDaoImpl implements LoginDao {
	String result = null;

	private static final Logger logger = LogManager.getLogger(CustomerDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Customer getCustomerByEmailAndPassword(String email, String password) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM com.busticketbooking.entity.Customer c WHERE c.email=:email";

			Query<Customer> query = session.createQuery(hql);
			query.setParameter("email", email);
			Customer customer = query.getResultList().get(0);
			System.out.println(customer);
			return customer;

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}
}
