package com.busticketbooking.dao;

import java.util.List;

import com.busticketbooking.dto.SeatDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Seat;
import com.busticketbooking.exception.IdNotFoundException;

public interface SeatDao {

	public String addSeat(Seat seat);

	public Seat getSeatById(Long id);

	public Seat getSeatByName(String seatName);
	
	public  List<Seat>  getSeatByStatus(String seatStatus);
	
	public String updateSeat(Seat seat);
	
	public boolean isSeatExists(long seatId);
	
	public List<Seat> getSeatByBusId(Bus bus);
	
	public String deleteSeatByBusId(Bus bus);

	public String updateStatus(String seatName,Bus bus);
	
}
