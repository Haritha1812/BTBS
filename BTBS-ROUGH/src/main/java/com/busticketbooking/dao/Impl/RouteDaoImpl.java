package com.busticketbooking.dao.Impl;

import java.util.List;

import javax.persistence.TemporalType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.busticketbooking.dao.BusDao;
import com.busticketbooking.dao.RouteDao;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.entity.Route;
@Repository
public class RouteDaoImpl implements RouteDao{
	String result = null;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private BusDao busDao;
	@Override
	public Route getRouteById(Long routeId) {
		Session session=sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		transaction.commit();
		return session.get(Route.class, routeId);
		
	}

	@Override
	public boolean isRouteExists(Long routeId) {
		Session session=sessionFactory.getCurrentSession();
	      Route route = session.get(Route.class, routeId);
		return (route != null);
	}

	@Override
	public List<Route> getAllRoutes() {
		Session session=sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
	
	 	   @SuppressWarnings("unchecked")
		Query<Route> query=session.createQuery(" FROM com.busticketbooking.entity.Route ");
	 		transaction.commit();
	 	   return (query.getResultList().isEmpty()?null:query.getResultList());
	}

	@Override
	public String deleteRoute(Long id) {
		Session session=sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
    @SuppressWarnings("unchecked")
	Query<Route> query = session.createQuery("DELETE FROM Route b where b.routeId=?1 ");
	query.setParameter(1, id);
	 
	int res = query.executeUpdate();
	transaction.commit();
	System.out.println(res);
	if (res == 0) {
		result="Deletion is not successful for id: "+id;
		} else {
			result="Deletion is successful for id: "+id;
		}
	return result;
	
	}
	

	@Override
	public String addRoute(Route route) {
		Session session=sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(route);
		transaction.commit();
		result="Route with route id "+route.getRouteId()+"added successfully";
		return result;
	}

	@Override
	public String updateRoute(Route route) {
        System.out.println("Updating.");

        Session session=sessionFactory.openSession();
        Transaction transaction = session.beginTransaction(); 
        session.update(route);
        transaction.commit();
        Long rId = route.getRouteId();
    
        return route.getRouteName()+" Updated successfully with Route Id: " + rId ;
	}

	@Override
	public Route getRouteByFromAndToLocation(String fromLocation, String toLocation) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM com.busticketbooking.entity.Route b WHERE b.fromLocation = :from AND b.toLocation =:to";

		Query<Route> query = session.createQuery(hql);
		query.setParameter("from", fromLocation);
		query.setParameter("to", toLocation);
		for (Route route : query.list()) {

			System.out.println(route);
			return route;

		}
		return null;
		
	}

	@Override
	public Route getRouteByName(String routeName) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM com.busticketbooking.entity.Route b WHERE b.routeName =:rname";
		
		Query<Route> query = session.createQuery(hql);
		query.setParameter("rname", routeName);
		for (Route route : query.list()) {

			System.out.println(route);
			return route;

		}
		return null;
		
	}

}
