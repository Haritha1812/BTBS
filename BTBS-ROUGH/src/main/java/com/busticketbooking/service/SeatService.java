package com.busticketbooking.service;

import java.util.List;

import com.busticketbooking.dto.SeatDto;
import com.busticketbooking.entity.Seat;

public interface SeatService {
	/**
	 * add seat
	 * 
	 * @param SeatDto
	 * @return
	 */
	String addSeat(SeatDto SeatDto);

	/**
	 * get seat by id
	 * 
	 * @param id
	 * @return
	 */
	Seat getSeatById(Long id);

	/**
	 * get seat by name
	 * 
	 * @param seatName
	 * @return
	 */
	Seat getSeatByName(String seatName);
	
	/**
	 * get all seat details
	 * 
	 * @param seatStatus
	 * @return
	 */
	List<Seat> getSeatByStatus(String seatStatus);

	/**
	 * update seat
	 * 
	 * @param SeatDto
	 * @return
	 */
	String updateSeat(SeatDto SeatDto);

	/**
	 * check if seat exists
	 * 
	 * @param seatId
	 * @return
	 */
	boolean isSeatExists(long seatId);

	/**
	 * get seat by bus id
	 * 
	 * @param busId
	 * @return
	 */
	List<Seat> getSeatByBusId(long busId);

	/**
	 * update status of seat once booked
	 * 
	 * @param seatName
	 * @param busId
	 * @return
	 */
	String updateStatus(int seatNumber, long busId);

}
