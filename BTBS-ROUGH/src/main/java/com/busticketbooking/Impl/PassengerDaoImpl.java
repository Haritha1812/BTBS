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
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.busticketbooking.dao.PassengerDao;
import com.busticketbooking.dto.PassengerDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Passenger;
import com.busticketbooking.exception.DuplicateEmailException;

@Repository
public class PassengerDaoImpl implements PassengerDao {
	String result=null;

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public String addPassenger(Passenger passenger) {
		Session session=sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(passenger);
		transaction.commit();
		result ="Passenger with Passenger number "+passenger.getId()+" added successfully";
    	session.close();
		return result;
	}
	
	
	  @Override public boolean isPassengerExists(Long id) { Session
	  session=sessionFactory.getCurrentSession(); Passenger pass =
	  session.get(Passenger.class, id); return (pass != null); }
	  
	  @Override public List<Passenger> getAllPassenger() { Session
	  session=sessionFactory.openSession();
	  
	  @SuppressWarnings("unchecked") String hql =
	  "FROM com.busticketbooking.entity.Passenger"; Query<Passenger>
	  query=session.createQuery(hql);
	  
	  return (query.getResultList().isEmpty()?null:query.getResultList()); }
	  
	  @Override public String deletePassenger(Long id) { Session
	  session=sessionFactory.openSession(); Transaction transaction =
	  session.beginTransaction();
	  
	  Passenger passenger; passenger=session.get(Passenger.class, id);
	  if(passenger!=null) { session.delete(passenger);
	  
	  session.flush(); result="Deletion is successful for id: "+id; }
	  transaction.commit(); session.close(); return result; }
	 
	
	
	  
	  @ExceptionHandler(DuplicateEmailException.class) public
	  ResponseEntity<String> duplicateIdFound(DuplicateEmailException e) { return
	  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); }
	 
	  
	  }
	 
	

