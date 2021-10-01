package com.busticketbooking.dao.Impl;


import static com.busticketbooking.util.LibraryManagementConstants.*;
import java.util.List;

import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.busticketbooking.controller.MailSend;
import com.busticketbooking.dao.BookTicketDao;
import com.busticketbooking.entity.BookTicket;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.exception.DatabaseException;

@Repository
public class BookTicketDaoImpl implements BookTicketDao{
	String result=null;

	private static final Logger logger = LogManager.getLogger(BookTicketDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public BookTicket getTicketById(Long id) {
		logger.info("Entering Get Ticket By Id Function");
		try {
		Session session=sessionFactory.getCurrentSession();
		
		return session.get(BookTicket.class,id);
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_FETCH);
	}
	}
	
	@Transactional
	@Override
	public boolean isTicketIdExists(Long id) {
		try {
		Session session=sessionFactory.getCurrentSession();
	     BookTicket bookTicket = session.get(BookTicket.class, id);
		return (bookTicket != null);
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_FETCH);
	}}

	@Transactional
	@Override
	public List<BookTicket> getAllTickets() {
		try {
		Session session=sessionFactory.getCurrentSession();
	 	   @SuppressWarnings("unchecked")
		   Query<BookTicket> query=session.createQuery("From com.busticketbooking.entity.BookTicket");
	 	  
			return (query.getResultList().isEmpty()?null:query.getResultList());
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_FETCH);
	}}
	@Transactional
	@Override
	public String deleteTicket(Long id) {

		logger.info("Entering Delete Ticket Function");
		try {
		Session session=sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		BookTicket bookticket;
		bookticket=session.get(BookTicket.class, id);
		if(bookticket!=null) {
			session.delete(bookticket);
			
			session.flush();
			result="Deletion is successful for id: "+id;
		}
		return result;}catch (Exception e) {
			throw new DatabaseException(ERROR_IN_DELETE);
		}

	}
	@Transactional
	@Override
	public String addTicket(BookTicket bookTicket) {

		logger.info("Entering Add Ticket Function");
		try {
		Session session=sessionFactory.getCurrentSession();
		session.save(bookTicket);
		
		result ="Bus with bus number "+bookTicket.getId()+" added successfully";
     	
		return result;
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_INSERT);
	}
	}
	@Transactional
	@Override
	public List<BookTicket> getTicketByCusId(Customer customer) {
		List<BookTicket> bookTicket =null;
		try {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select i from BookTicket i WHERE i.customer=?1";

		Query<BookTicket> query = session.createQuery(hql);
		query.setParameter(1, customer);
		
		return (query.getResultList().isEmpty()?null:query.getResultList());
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_FETCH);
	}}
	@Transactional
	@Override
	public Bus getTicketByCustomerId(long customerId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	@Override
	public String updateBookingStatus(long id,Bus bus,Customer customer) {

		logger.info("Entering Update Ticket  Function");
		try {
		Session session = sessionFactory.getCurrentSession();
       BookTicket bookTicket = getTicketById(id);
		System.out.println("booking status called...");
		System.out.println(bookTicket);

		Query q=session.createQuery("update BookTicket set bookingStatus=?3 where bus=?1 AND customer=?2 AND id=?4");  
		
		q.setParameter(4,id);
		q.setParameter(2,customer);  
		q.setParameter(1,bus);  
		q.setParameter(3,"Confirmed");
		int status=q.executeUpdate(); 
	
		
		 
		 return id+" Updated successfully with bus Id: " + bus.getId() ;
		
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_UPDATE);
	}}

}
