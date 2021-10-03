package com.busticketbooking.dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.busticketbooking.dao.SeatDao;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;
import com.busticketbooking.entity.Seat;

@Repository
public class SeatDaoImpl implements SeatDao {
	String result = null;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String addSeat(Seat seat) {
		System.out.println("SeatDao called");
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(seat);
		transaction.commit();
		result = "Seat with Seat number " + seat.getId() + " added successfully";
		session.close();
		return result;
	}

	@Override
	public Seat getSeatById(Long id) {
		Session session = sessionFactory.getCurrentSession();

		return session.get(Seat.class, id);
	}

	@Override
	public Seat getSeatByName(String seatName) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM com.busticketbooking.entity.Seat b WHERE b.seatName = :seat ";

		Query<Seat> query = session.createQuery(hql);
		query.setParameter("seat", seatName);

		for (Seat seat : query.list()) {

			System.out.println(seat);
			return seat;

		}
		return null;
	}

	@Override
	public List<Seat> getSeatByStatus(String seatStatus) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Seat> resultList = session.createQuery("select i from Seat i where i.seatStatus=:status")
				.setParameter("status", seatStatus).getResultList();
		System.out.println(resultList);
		return (resultList.isEmpty() ? null : resultList);

	}

	@Override
	public String updateSeat(Seat seat) {
		System.out.println("updateSeat.. ");

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(seat);
		transaction.commit();
		Long sId = seat.getId();

		return seat.getSeatName() + " Updated successfully with  Id: " + sId;
	}

	@Override
	public boolean isSeatExists(long seatId) {
		Session session = sessionFactory.getCurrentSession();
		Seat seat = session.get(Seat.class, seatId);
		return (seat != null);
	}

	@Override
	public List<Seat> getSeatByBusId(Bus bus) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Seat> resultList = session.createQuery("select i from Seat i where i.bus=:bus").setParameter("bus", bus)
				.getResultList();
		System.out.println(resultList);
		return (resultList.isEmpty() ? null : resultList);
	}

	@Override
	public String updateStatus(String seatName, Bus bus) {
		Session session = sessionFactory.getCurrentSession();

		Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("update Seat set seatStatus=:status where bus=:bus AND seatName=:name");
		q.setParameter("name", seatName);
		q.setParameter("bus", bus);
		q.setParameter("status", "No");
		int status = q.executeUpdate();
		transaction.commit();
		return seatName + " Updated successfully with bus Id: " + bus.getId();

	}

	@Override
	public String deleteSeatByBusId(Bus bus) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		@SuppressWarnings("unchecked")
		Query<Route> query = session.createQuery("DELETE FROM Seat b where b.bus=:bus ");
		query.setParameter("bus", bus);

		int res = query.executeUpdate();
		transaction.commit();
		System.out.println(res);
		if (res == 0) {
			result = "Deletion is not successful for id: " + bus.getId();
		} else {
			result = "Deletion is successful for id: " + bus.getId();
		}
		return result;

	}
}
