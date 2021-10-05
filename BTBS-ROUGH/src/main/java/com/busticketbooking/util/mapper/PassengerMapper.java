package com.busticketbooking.util.mapper;

import com.busticketbooking.dto.PassengerDto;
import com.busticketbooking.dto.RouteDto;
import com.busticketbooking.entity.Passenger;
import com.busticketbooking.entity.Route;

public class PassengerMapper {

	public static Passenger dtoToEntity(PassengerDto p) {

		Passenger passenger = new Passenger();
		passenger.setId(p.getId());
		passenger.setName(p.getName());
		passenger.setAge(p.getAge());
		passenger.setGender(p.getGender());
		passenger.setSeatNumber(p.getSeatNumber());
		passenger.setCustomer(p.getCustomer());
		passenger.setBus(p.getBus());
		return passenger;

	}

}
