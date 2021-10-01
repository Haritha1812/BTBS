package com.busticketbooking.dao.Impl;

import static com.busticketbooking.util.LibraryManagementConstants.ERROR_IN_DELETE;
import static com.busticketbooking.util.LibraryManagementConstants.ERROR_IN_FETCH;
import static com.busticketbooking.util.LibraryManagementConstants.ERROR_IN_INSERT;
import static com.busticketbooking.util.LibraryManagementConstants.ERROR_IN_UPDATE;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.busticketbooking.dao.BusDao;
import com.busticketbooking.dao.SeatDao;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.entity.Route;
import com.busticketbooking.exception.DatabaseException;
@Repository
public class BusDaoImpl implements BusDao {
	String result=null;


	private static final Logger logger = LogManager.getLogger(BusDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	SeatDao seatDao;
	@Transactional
	@Override
	public Bus getBusById(Long busId) {
		try {
		Session session=sessionFactory.getCurrentSession();
	
			return session.get(Bus.class, busId);
	} catch (Exception e) {
		throw new DatabaseException(ERROR_IN_FETCH);
	}
}
	@Transactional
	@Override
	public boolean isBusExists(Long busId) {
		try {
		Session session=sessionFactory.getCurrentSession();
 	      Bus bus = session.get(Bus.class, busId);
		return (bus != null);
	}
		catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}
	@Transactional
	@Override
	public List<Bus> getAllBuses() {
		try {
		Session session=sessionFactory.getCurrentSession();
 	   @SuppressWarnings("unchecked")
	   Query<Bus> query=session.createQuery("From com.busticketbooking.entity.Bus");
 	  
		return (query.getResultList().isEmpty()?null:query.getResultList());
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_FETCH);
	}}

	@Transactional
	@Override
	public String addBus(Bus bus) {

		logger.info("Entering Add bus Function");
		try {
		Session session=sessionFactory.getCurrentSession();
		session.save(bus);
		result ="Bus with bus number "+bus.getId()+" added successfully";

		return result;
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_INSERT);
	}
	}
	@Transactional
	@Override
	public String deleteBus(Long id) {

		logger.info("Entering Delete bus Function");
		try{ 
		Session session=sessionFactory.openSession();
		
		Query<Bus> query = session.createQuery("DELETE FROM Bus b where b.id=?1 ");
		query.setParameter(1, id);
		 
		int res = query.executeUpdate();
		System.out.println(res);
		if (res == 0) {
			result="Deletion is not successful for id: "+id;
			} else {
				result="Deletion is successful for id: "+id;
			}
		return result;
		
}catch (Exception e) {
	throw new DatabaseException(ERROR_IN_DELETE);
}

}
	@Transactional
	@Override
	public String updateBus(Bus bus) {
		logger.info("Entering Update bus Function");
try {
        Session session=sessionFactory.openSession();
        session.update(bus);
        Long bId = bus.getId();
    
        return bus.getName()+" Updated successfully with  Id: " + bId ;
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_UPDATE);
	}}
	@Transactional
	@Override
	public List<Bus> getBusByFromAndToLocation(Route route,Date date) {
		try {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Bus> resultList = session.createQuery("select i from Bus i where i.route=?1 and i.date=?2")
				.setParameter(2, date).setParameter(1, route).getResultList();
		System.out.println(resultList);
		return (resultList.isEmpty() ? null : resultList);
		
		
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_FETCH);
	}}
	@Transactional
	@Override
	public Bus getBusByBusName(String busName) {
		try {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select i from Bus i WHERE i.name=?1";

		Query<Bus> query = session.createQuery(hql);
		query.setParameter(1, busName);
		
		return (query.getResultList().isEmpty()?null:query.getResultList().get(0));
		
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_FETCH);
	}}
	@Transactional
	@Override
	public String deleteBusByRouteId(Route route) {

		logger.info("Entering Delete bus by Route Function");
		try {
		Session session=sessionFactory.getCurrentSession();
	
		
    @SuppressWarnings("unchecked")
	Query<Route> query = session.createQuery("DELETE FROM Bus b where b.route=?1 ");
	query.setParameter(1, route);
	 
	int res = query.executeUpdate();
	
	System.out.println(res);
	if(res==0) {
		result="Deletion is not successful for id: "+route.getRouteId();
	}
	else {
		result="Deletion is successful for id: "+route.getRouteId();
	}
	return result;
	
	}catch (Exception e) {
		throw new DatabaseException(ERROR_IN_DELETE);
	}

}
	

}