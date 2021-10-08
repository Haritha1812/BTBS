package com.busticketbooking.util.mapper;

import com.busticketbooking.dto.SeatDto;
import com.busticketbooking.entity.Seat;

public class SeatMapper {

	private SeatMapper() {

	}

	public static Seat dtoToEntity(SeatDto s) {

		Seat seat = new Seat();
		seat.setId(s.getId());
		seat.setSeatNumber(s.getSeatNumber());
		seat.setSeatStatus(s.getSeatStatus());
		seat.setBus(s.getBus());
		return seat;

	}
}
