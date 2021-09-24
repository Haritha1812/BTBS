package com.busticketbooking.Impl;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.busticketbooking.dao.BusDao;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.entity.Route;

@Repository
public class BusDaoImpl implements BusDao {
	String result=null;

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Bus getBusById(Long busId) {
		Session session=sessionFactory.openSession();
	
			return session.get(Bus.class, busId);
	}

	@Override
	public boolean isBusExists(Long busId) {
		Session session=sessionFactory.openSession();
 	      Bus bus = session.get(Bus.class, busId);
		return (bus != null);
	}

	@Override
	public List<Bus> getAllBuses() {
		Session session=sessionFactory.openSession();
 	   @SuppressWarnings("unchecked")
	   Query<Bus> query=session.createQuery("From com.busticketbooking.entity.Bus");
 	  
		return (query.getResultList().isEmpty()?null:query.getResultList());
	}


	@Override
	public String addBus(Bus bus) {
		System.out.println("BusDao called");
		Session session=sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(bus);
		transaction.commit();
		result ="Bus with bus number "+bus.getId()+" added successfully";
     	session.close();
		return result;
	}

	@Override
	public String deleteBus(Long id) {
		Session session=sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Bus busObj;
		busObj=session.get(Bus.class, id);
		if(busObj!=null) {
			session.delete(busObj);
			
			session.flush();
			result="Deletion is successful for id: "+id;
		}
		transaction.commit();
		session.close();
		return result;
}

	@Override
	public String updateBus(Bus bus) {
        System.out.println("updateBus.. ");

        Session session=sessionFactory.openSession();
        Transaction transaction = session.beginTransaction(); 
        session.update(bus);
        transaction.commit();
        Long bId = bus.getId();
        session.close();
        return bus.getName()+" Updated successfully with  Id: " + bId ;
	}

	@Override
	public List<Bus> getBusByFromAndToLocation(Route route,Date date) {
		
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Bus> resultList = session.createQuery("select i from Bus i where i.route=?1 and i.date=?2")
				.setParameter(2, date).setParameter(1, route).getResultList();
		System.out.println(resultList);
		return (resultList.isEmpty() ? null : resultList);
		
		
	}
	

}