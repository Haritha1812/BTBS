package com.busticketbooking.dao;

import java.util.List;

import com.busticketbooking.dto.SeatDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Seat;
import com.busticketbooking.exception.IdNotFoundException;

public interface SeatDao {

	/**
	 * add seat
	 * @param seat
	 * @return
	 */
	public String addSeat(Seat seat);

	/**
	 * get seat by id
	 * @param id
	 * @return
	 */
	public Seat getSeatById(Long id);

	/**
	 * get seat by name
	 * @param seatName
	 * @return
	 */
	public Seat getSeatByName(String seatName);
	
	/**
	 * get seat status by id
	 * @param seatStatus
	 * @return
	 */
	public  List<Seat>  getSeatByStatus(String seatStatus);
	
	/**
	 * update seat
	 * @param seat
	 * @return
	 */
	public String updateSeat(Seat seat);
	
	/**
	 * check whether seat exists
	 * @param seatId
	 * @return
	 */
	public boolean isSeatExists(long seatId);
	
	/**
	 * get seat by bus id
	 * @param bus
	 * @return
	 */
	public List<Seat> getSeatByBusId(Bus bus);
	
	/**
	 * delete seat by bus id
	 * @param bus
	 * @return
	 */
	public String deleteSeatByBusId(Bus bus);

	/**
	 * update status
	 * @param seatName
	 * @param bus
	 * @return
	 */
	public String updateStatus(String seatName,Bus bus);
	
}
