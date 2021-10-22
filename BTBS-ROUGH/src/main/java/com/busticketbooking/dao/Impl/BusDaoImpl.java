package com.busticketbooking.dao.Impl;

import static com.busticketbooking.util.BusTicketBookingConstants.ERROR_IN_DELETE;
import static com.busticketbooking.util.BusTicketBookingConstants.ERROR_IN_FETCH;
import static com.busticketbooking.util.BusTicketBookingConstants.ERROR_IN_INSERT;
import static com.busticketbooking.util.BusTicketBookingConstants.ERROR_IN_UPDATE;

import java.util.Date;
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
import com.busticketbooking.dao.SeatDao;
import com.busticketbooking.dto.BusDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;
import com.busticketbooking.exception.DatabaseException;

@Repository
public class BusDaoImpl implements BusDao {
	String result = null;

	private static final Logger logger = LogManager.getLogger(BusDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	SeatDao seatDao;

	@Override
	public Bus getBusById(Long busId) {

		logger.info("Entering Get Bus by Id  Function in dao");
		try {
			Session session = sessionFactory.getCurrentSession();

			return session.get(Bus.class, busId);
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public boolean isBusExists(Long busId) {
		logger.info("Entering  Busexists  Function in dao");
		try {
			Session session = sessionFactory.getCurrentSession();
			Bus bus = session.get(Bus.class, busId);
			return (bus != null);
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<Bus> getAllBuses() {
		logger.info("Entering Get All Bus  Function in dao");
		try {
			Session session = sessionFactory.getCurrentSession();

			Query<Bus> query = session.createQuery("From Bus order by name");

			return (query.getResultList().isEmpty() ? null : query.getResultList());
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Transactional
	@Override
	public String addBus(Bus bus) {

		logger.info("Entering Add bus Function in dao");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(bus);
			result = "Bus with bus number " + bus.getId() + " added successfully";

			return result;
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_INSERT);
		}
	}

	@Transactional
	@Override
	public String deleteBus(Long id) {

		logger.info("Entering Delete bus Function in dao");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query<Bus> query = session.createQuery("DELETE FROM Bus b where b.id=:id ");
			query.setParameter("id", id);

			int res = query.executeUpdate();
			session.getTransaction().commit();
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

	@Override
	public String updateBus(Bus bus) {
		logger.info("Entering Update bus Function in dao");
		try {
			Session session = sessionFactory.openSession();

			Transaction transaction = session.beginTransaction();
			session.update(bus);
			Long bId = bus.getId();
			transaction.commit();
			session.close();
			return bus.getName() + " Updated successfully with  Id: " + bId;
		} catch (Exception e) {

			logger.error("Error in update bus Function in dao");
			throw new DatabaseException(ERROR_IN_UPDATE);
		}
	}



	@Override
	public List<Bus> getBusByFromAndToLocation(Route route, Date date) {
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Bus> resultList = session.createQuery("select i from Bus i where i.route=:route and i.date=:date")
					.setParameter("date", date).setParameter("route", route).getResultList();

			return (resultList);

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public Bus getBusByBusName(String busName) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "select i from Bus i WHERE i.name=:name";

			Query<Bus> query = session.createQuery(hql);
			query.setParameter("name", busName);

			return (query.getResultList().isEmpty() ? null : query.getResultList().get(0));

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Transactional
	@Override
	public String deleteBusByRouteId(Route route) {

		logger.info("Entering Delete bus by Route Function in dao");
		try {
			Session session = sessionFactory.getCurrentSession();

			Query<Route> query = session.createQuery("DELETE FROM Bus b where b.route=:route ");
			query.setParameter("route", route);

			int res = query.executeUpdate();

			System.out.println(res);
			if (res == 0) {
				result = "Deletion is not successful for id: " + route.getRouteId();
			} else {
				result = "Deletion is successful for id: " + route.getRouteId();
			}
			return result;

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_DELETE);
		}

	}

	@Override
	public List<String> getbustypes() {
		try {
			Session session = sessionFactory.getCurrentSession();

			Query<String> query = session.createNativeQuery("select * from public.bustype");

			return (query.getResultList());
			}
			 catch (Exception e) {
					throw new DatabaseException(ERROR_IN_FETCH);
				}
			}

	@Override
	public String updateBusTimings(Bus bus) {
		logger.info("Entering Update bus timings Function in dao");
		try {
			Session session = sessionFactory.openSession();

			Transaction transaction = session.beginTransaction();
			session.update(bus);
			Long bId = bus.getId();
			transaction.commit();
			session.close();
			return bus.getName() + " Updated successfully with  Id: " + bId;
		} catch (Exception e) {

			logger.error("Error in update bus timings Function in dao");
			throw new DatabaseException(ERROR_IN_UPDATE);
		}
	}

	}

