package com.busticketbooking.util.mapper;

import com.busticketbooking.dto.BusDto;
import com.busticketbooking.entity.Bus;

public class BusMapper {
	private BusMapper() {

	}

	public static Bus dtoToEntity(BusDto b) {

		Bus bus = new Bus();
		bus.setId(b.getId());
		bus.setName(b.getName());
		bus.setNumberOfSeats(b.getNumberOfSeats());
		bus.setBusType(b.getBusType());
		bus.setRoute(b.getRoute());
		bus.setDate(b.getDate());
		bus.setArrivalTime(b.getArrivalTime());
		bus.setDepartureTime(b.getDepartureTime());
		bus.setFare(b.getFare());
		return bus;

	}

}
