package com.busticketbooking.service;

import java.util.List;

import com.busticketbooking.dto.BusDto;
import com.busticketbooking.dto.SeatDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Seat;
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.IdNotFoundException;

public interface SeatService {
	public String addSeat(SeatDto SeatDto);

	public Seat getSeatById(Long id)throws BusinessLogicException;

	public Seat getSeatByName(String seatName);
	
	public  List<Seat>  getSeatByStatus(String seatStatus);
	
	public String updateSeat(SeatDto SeatDto)throws BusinessLogicException;
	
	public boolean isSeatExists(long seatId);
	
	public List<Seat> getSeatByBusId(long busId);
	
	public String updateStatus(String seatName,long busId);
	
}
