package com.busticketbooking.util.mapper;

import com.busticketbooking.dto.RouteDto;
import com.busticketbooking.dto.SeatDto;
import com.busticketbooking.entity.Route;
import com.busticketbooking.entity.Seat;

public class SeatMapper {

	private SeatMapper() {

	}

	public static Seat dtoToEntity(SeatDto s) {

		Seat seat = new Seat();
		seat.setId(s.getId());
		seat.setSeatName(s.getSeatName());
		seat.setSeatStatus(s.getSeatStatus());
		seat.setBus(s.getBus());
		return seat;

	}
}
