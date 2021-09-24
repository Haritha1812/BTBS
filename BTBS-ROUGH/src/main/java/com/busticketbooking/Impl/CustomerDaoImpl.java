package com.busticketbooking.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.busticketbooking.dao.CustomerDao;
import com.busticketbooking.dao.CustomerDao;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.entity.Route;
import com.busticketbooking.exception.DuplicateEmailException;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	String result = null;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean isCustomerExists(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Customer pass = session.get(Customer.class, id);
		return (pass != null);
	}

	@Override
	public List<Customer> getAllCustomer() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		String hql = "FROM com.busticketbooking.entity.Customer";
		Query<Customer> query = session.createQuery(hql);

		return (query.getResultList().isEmpty() ? null : query.getResultList());
	}

	@Override
	public String deleteCustomer(Long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Customer customer;
		customer = session.get(Customer.class, id);
		if (customer != null) {
			session.delete(customer);

			session.flush();
			result = "Deletion is successful for id: " + id;
		}
		transaction.commit();
		session.close();
		return result;
	}

	@Override
	public String addCustomer(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(customer);
		transaction.commit();
		result = "Customer with Customer number " + customer.getId() + " added successfully";
		session.close();
		return result;
	}

	@Override
	public String updateCustomer(Customer customer) {
		System.out.println("updateBus.. ");

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(customer);
		transaction.commit();
		Long CustomerId = customer.getId();

		return customer.getName() + " Updated successfully with  Id: " + CustomerId;
	}

	@Override
	public Customer isCustomerEmailExists(String email) {

		Session session = sessionFactory.openSession();
		String hql = "FROM com.busticketbooking.entity.Customer C WHERE C.email = :cemail";

		Query<Customer> query = session.createQuery(hql);
		query.setParameter("cemail", email);

//		List results = query.list();
		for (Customer Customer : query.list()) {
			return Customer;
		}
		return null;
	}

	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<String> duplicateIdFound(DuplicateEmailException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@Override
	public Customer getCustomerByMobileNumber(String mobileNumber) {
		Session session = sessionFactory.openSession();
		String hql = "FROM com.busticketbooking.entity.Customer c WHERE c.mobileNumber = :mobno";

		Query<Customer> query = session.createQuery(hql);
		query.setParameter("mobno", mobileNumber);

//			List results = query.list();
		for (Customer customer : query.list()) {
			return customer;
		}
		return null;

	}

	@Override
	public Customer getCustomerById(Long id) {
		Session session=sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.commit();
		return session.get(Customer.class, id);
	}
}
