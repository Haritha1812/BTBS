package com.busticketbooking.dao.Impl;

import static com.busticketbooking.util.BusTicketBookingConstants.ERROR_IN_DELETE;
import static com.busticketbooking.util.BusTicketBookingConstants.ERROR_IN_FETCH;
import static com.busticketbooking.util.BusTicketBookingConstants.ERROR_IN_INSERT;
import static com.busticketbooking.util.BusTicketBookingConstants.ERROR_IN_UPDATE;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.busticketbooking.dao.BusDao;
import com.busticketbooking.dao.RouteDao;
import com.busticketbooking.entity.Route;
import com.busticketbooking.exception.DatabaseException;

@Repository
public class RouteDaoImpl implements RouteDao {
	String result = null;

	private static final Logger logger = LogManager.getLogger(RouteDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private BusDao busDao;

	@Transactional
	@Override
	public Route getRouteById(Long routeId) {
		logger.info("Entering Get route by Id  Function");
		try {

			Session session = sessionFactory.getCurrentSession();

			return session.get(Route.class, routeId);

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public boolean isRouteExists(Long routeId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Route route = session.get(Route.class, routeId);
			return (route != null);
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<Route> getAllRoutes() {
		logger.info("Entering get all routes Function");
		Session session = sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		Query<Route> query = session.createQuery(" FROM com.busticketbooking.entity.Route ");
		return (query.getResultList().isEmpty() ? null : query.getResultList());
	}

	@Override
	public String deleteRoute(Long id) {
		logger.info("Entering Delete route Function");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction transaction = session.beginTransaction();

			@SuppressWarnings("unchecked")
			Query<Route> query = session.createQuery("DELETE FROM Route b where b.routeId=:id ");
			query.setParameter("id", id);

			int res = query.executeUpdate();
			transaction.commit();
			System.out.println(res);
			if (res == 0) {
				result = "Deletion is not successful for id: " + id;
			} else {
				result = "Deletion is successful for id: " + id;
			}
			return result;

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_DELETE);
		}

	}

	@Transactional
	@Override
	public String addRoute(Route route) {
		logger.info("Entering add route Function");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(route);
			result = "Route with route id " + route.getRouteId() + "added successfully";
			return result;
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_INSERT);
		}
	}

	@Override
	public String updateRoute(Route route) {
		logger.info("Entering update route Function");
		try {
			Session session = sessionFactory.openSession();

			Transaction transaction = session.beginTransaction();
			session.update(route);

			Long rId = route.getRouteId();
			transaction.commit();
			return route.getRouteName() + " Updated successfully with Route Id: " + rId;
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_UPDATE);
		}
	}

	@Override
	public Route getRouteByFromAndToLocation(String fromLocation, String toLocation) {
		logger.info("Entering search bus Function");
		try {
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

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public Route getRouteByName(String routeName) {
		logger.info("Entering search route by name Function");
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM com.busticketbooking.entity.Route b WHERE b.routeName =:rname";

			Query<Route> query = session.createQuery(hql);
			query.setParameter("rname", routeName);
			for (Route route : query.list()) {

				System.out.println(route);
				return route;

			}
			return null;

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

}
