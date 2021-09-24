package com.busticketbooking.Impl;

import java.util.List;

import javax.persistence.TemporalType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.busticketbooking.dao.BookTicketDao;
import com.busticketbooking.entity.BookTicket;
import com.busticketbooking.entity.Bus;

public class BookTicketDaoImpl implements BookTicketDao{
	String result=null;

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public BookTicket getTicketById(Long id) {
		Session session=sessionFactory.openSession();
		
		return session.get(BookTicket.class,id);
	}

	@Override
	public boolean isTicketIdExists(Long id) {
		Session session=sessionFactory.openSession();
	     BookTicket bookTicket = session.get(BookTicket.class, id);
		return (bookTicket != null);
	}

	
	@Override
	public List<BookTicket> getAllTickets() {
		Session session=sessionFactory.openSession();
	 	   @SuppressWarnings("unchecked")
		   Query<BookTicket> query=session.createQuery("From com.busticketbooking.entity.BookTicket");
	 	  
			return (query.getResultList().isEmpty()?null:query.getResultList());
	}

	@Override
	public String deleteTicket(Long id) {
		Session session=sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		BookTicket bookticket;
		bookticket=session.get(BookTicket.class, id);
		if(bookticket!=null) {
			session.delete(bookticket);
			
			session.flush();
			result="Deletion is successful for id: "+id;
		}
		transaction.commit();
		session.close();
		return result;
	}

	@Override
	public String addTicket(BookTicket bookTicket) {
		Session session=sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(bookTicket);
		transaction.commit();
		result ="Bus with bus number "+bookTicket.getId()+" added successfully";
     	session.close();
		return result;
	}

	@Override
	public List<Bus> getTicketByBusId(long busId) {
		Session session = sessionFactory.openSession();
		String hql = "FROM com.busticketbooking.entity.BookTicket b WHERE b.bus_id : id";

		Query<Bus> query = session.createQuery(hql);
		query.setParameter("id", busId);
		return (query.getResultList().isEmpty()?null:query.getResultList());
	}

	@Override
	public Bus getTicketByCustomerId(long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
