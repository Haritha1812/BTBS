package com.busticketbooking.service.Impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busticketbooking.dao.BusDao;
import com.busticketbooking.dao.SeatDao;
import com.busticketbooking.dao.Impl.BusDaoImpl;
import com.busticketbooking.dto.BusDto;
import com.busticketbooking.dto.SeatDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;
import com.busticketbooking.entity.Seat;
import com.busticketbooking.exception.BusinessLogicException;
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
	private static final Logger logger = LogManager.getLogger(SeatServiceImpl.class);

    
	@Override
	public String addSeat(SeatDto seatDto) {
		logger.info("Entering add seat  Function in dao");
		Seat seat = SeatMapper.dtoToEntity(seatDto);
		System.out.println(seat.getBus());
		Bus bus = busDao.getBusById(seat.getBus().getId());
				seat.setBus(bus);
				return seatDao.addSeat(seat);
	}

	@Override
	public Seat getSeatById(Long id)  {
		logger.info("Entering Get Seat by Id  Function in dao");
		if (seatDao.isSeatExists(id)) {
			return seatDao.getSeatById(id);
		} else {
			throw new BusinessLogicException("Seat with seat Id : " + id+ " Not found");
		}
	}

	@Override
	public Seat getSeatByName(String seatName) {
		logger.info("Entering Get Seat by Name  Function in dao");
		if(seatDao.getSeatByName(seatName)!=null)
	      return seatDao.getSeatByName(seatName);
	else 
		throw new BusinessLogicException("Seat with seat Name : " + seatName+ " Not found");
	
}
	@Override
	public  List<Seat>  getSeatByStatus(String seatStatus) {
		logger.info("Entering Get Seat by status  Function in dao");
		
	   if( seatDao.getSeatByStatus(seatStatus)!=null)
		   return seatDao.getSeatByStatus(seatStatus);
	   else 
			throw new BusinessLogicException("Seat with seat Name : " + seatStatus+ " Not found");
		
	}

	@Override
	public String updateSeat(SeatDto seatDto) {

		logger.info("Entering Update seat Function in dao");
		long seatId = seatDto.getId();
		if (seatDao.isSeatExists(seatId)) {
			Seat seat = SeatMapper.dtoToEntity(seatDto);
			Bus bus = busDao.getBusById(seat.getBus().getId());
					seat.setBus(bus);
			return seatDao.updateSeat(seat);
		} else {
			throw new BusinessLogicException("Seat with seat Id : " + seatId + " Not found");
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
