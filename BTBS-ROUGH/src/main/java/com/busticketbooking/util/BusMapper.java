package com.busticketbooking.util;

import com.busticketbooking.dto.BusDto;
import com.busticketbooking.entity.Bus;

public class BusMapper {
	
	public static Bus dtoToEntity(BusDto b) {
		
		Bus bus = new Bus();
		bus.setId(b.getId());
		bus.setName(b.getName());
		bus.setNumberOfSeats(b.getNumberOfSeats());
		bus.setBusType(b.getBusType());
		bus.setRouteName(b.getRouteName());
		bus.setRoute(b.getRoute());
		bus.setDate(b.getDate());
		bus.setArrivalTime(b.getArrivalTime());
		bus.setDepartureTime(b.getDepartureTime());
		bus.setFare(b.getFare());
		return bus;
		
	}
	
	public static BusDto entityToDto(Bus b) {
		
		BusDto busDto = new BusDto();
		busDto.setId(b.getId());
		busDto.setName(b.getName());
		busDto.setBusType(b.getBusType());
		busDto.setNumberOfSeats(b.getNumberOfSeats());
		busDto.setRouteName(b.getRouteName());
		return busDto;
	}
}
