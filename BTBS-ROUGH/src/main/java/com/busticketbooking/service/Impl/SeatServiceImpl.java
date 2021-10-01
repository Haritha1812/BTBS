package com.busticketbooking.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busticketbooking.dao.BusDao;
import com.busticketbooking.dao.SeatDao;
import com.busticketbooking.dto.BusDto;
import com.busticketbooking.dto.SeatDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;
import com.busticketbooking.entity.Seat;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.service.SeatService;
import com.busticketbooking.util.mapper.BusMapper;
import com.busticketbooking.util.mapper.SeatMapper;

@Service
public class SeatServiceImpl implements SeatService{
    @Autowired
	BusDao busDao;
    @Autowired
    SeatDao seatDao;
	@Override
	public String addSeat(SeatDto seatDto) {
		System.out.println("seat service called....");
		Seat seat = SeatMapper.dtoToEntity(seatDto);
		System.out.println("..................");
		System.out.println(seat.getBus());
		Bus bus = busDao.getBusById(seat.getBus().getId());
		System.out.println(bus);
				System.out.println("seat service....");
				seat.setBus(bus);
				return seatDao.addSeat(seat);
	}

	@Override
	public Seat getSeatById(Long id) throws IdNotFoundException {
		if (seatDao.isSeatExists(id)) {
			return seatDao.getSeatById(id);
		} else {
			throw new IdNotFoundException("Seat with seat Id : " + id+ " Not found");
		}
	}

	@Override
	public Seat getSeatByName(String seatName) {
	return seatDao.getSeatByName(seatName);
	}

	@Override
	public  List<Seat>  getSeatByStatus(String seatStatus) {
	return seatDao.getSeatByStatus(seatStatus);
	}

	@Override
	public String updateSeat(SeatDto seatDto) throws IdNotFoundException {
		long seatId = seatDto.getId();
		if (seatDao.isSeatExists(seatId)) {
			Seat seat = SeatMapper.dtoToEntity(seatDto);
			Bus bus = busDao.getBusById(seat.getBus().getId());
					seat.setBus(bus);
			return seatDao.updateSeat(seat);
		} else {
			throw new IdNotFoundException("Bus with bus Id : " + seatId + " Not found");
		}
	}

	@Override
	public boolean isSeatExists(long seatId) {
		
		return seatDao.isSeatExists(seatId);
	}

	@Override
	public List<Seat> getSeatByBusId(long busId) {
		Bus bus = busDao.getBusById(busId);
		
		return seatDao.getSeatByBusId(bus);
	}

	@Override
	public String updateStatus(String seatName, long busId) {
	Bus bus=	busDao.getBusById(busId);
		return seatDao.updateStatus(seatName, bus);
	}
}
