package com.busticketbooking.dao.Impl;

import static com.busticketbooking.util.LibraryManagementConstants.ERROR_IN_DELETE;
import static com.busticketbooking.util.LibraryManagementConstants.ERROR_IN_FETCH;
import static com.busticketbooking.util.LibraryManagementConstants.ERROR_IN_INSERT;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.busticketbooking.controller.MailSend;
import com.busticketbooking.dao.CustomerDao;
import com.busticketbooking.dao.CustomerDao;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.entity.Route;
import com.busticketbooking.exception.DatabaseException;
import com.busticketbooking.exception.DuplicateEmailException;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	String result = null;

	private static final Logger logger = LogManager.getLogger(CustomerDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public boolean isCustomerExists(Long id) {
		try {
		Session session = sessionFactory.getCurrentSession();
		Customer pass = session.get(Customer.class, id);
		return (pass != null);
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_FETCH);
	}}
	@Transactional
	@Override
	public List<Customer> getAllCustomer() {
		try {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "FROM com.busticketbooking.entity.Customer";
		Query<Customer> query = session.createQuery(hql);

		return (query.getResultList().isEmpty() ? null : query.getResultList());
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_FETCH);
	}}
	@Transactional
	@Override
	public String deleteCustomer(Long id) {

		logger.info("Entering Delete Customer Function");
		try {
		Session session = sessionFactory.getCurrentSession();
		
		Customer customer;
		customer = session.get(Customer.class, id);
		if (customer != null) {
			session.delete(customer);

			session.flush();
			result = "Deletion is successful for id: " + id;
		}
		session.close();
		return result;
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_DELETE);
	}

	}
	@Transactional
	@Override
	public String addCustomer(Customer customer) {

		logger.info("Entering Add Customer Function");
		try {
		Session session = sessionFactory.getCurrentSession();
			session.save(customer);
		result = "Customer with Customer number " + customer.getId() + " added successfully";
		session.close();
		return result;
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_INSERT);
	}
	}
	@Transactional
	@Override
	public String updateCustomer(Customer customer) {

		logger.info("Entering Update Customer Function");
try {
		Session session = sessionFactory.getCurrentSession();
			session.update(customer);
		Long CustomerId = customer.getId();

		return customer.getName() + " Updated successfully with  Id: " + CustomerId;
	}
	catch (Exception e) {
		throw new DatabaseException(ERROR_IN_FETCH);
	}}
	@Transactional
	@Override
	public Customer isCustomerEmailExists(String email) {
		
     try{
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM com.busticketbooking.entity.Customer C WHERE C.email = :cemail";

		Query<Customer> query = session.createQuery(hql);
		query.setParameter("cemail", email);
		return (query.getResultList().isEmpty()?null:query.getResultList().get(0));
}
catch (Exception e) {
	throw new DatabaseException(ERROR_IN_FETCH);
}}
	
	@Transactional
	@Override
	public Customer getCustomerByMobileNumber(String mobileNumber) {
		try {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM com.busticketbooking.entity.Customer c WHERE c.mobileNumber = :mobno";

		Query<Customer> query = session.createQuery(hql);
		query.setParameter("mobno", mobileNumber);

		return (query.getResultList().isEmpty()?null:query.getResultList().get(0));

	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_FETCH);
	}}

	@Override
	public Customer getCustomerById(Long id) {
		try {
	
		Session session=sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		transaction.commit();
		return session.get(Customer.class, id);
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_FETCH);
	}}

	@Override
	public Customer getCustomerByEmailAndPassword(String email, String password) {
		try {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM com.busticketbooking.entity.Customer c WHERE c.email = :email AND c.password = :pass";

		Query<Customer> query = session.createQuery(hql);
		query.setParameter("email", email);
		query.setParameter("pass", password);

		return (query.getResultList().isEmpty() ? null : query.getResultList().get(0));


	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_FETCH);
	}}

	@Override
	public Customer forgetPassword(String email) {
		
		Customer customer = isCustomerEmailExists(email);
		if(customer != null) {
			 
			 return customer;
		}
		
	else {
	return	null;}
		
		
	}
}
