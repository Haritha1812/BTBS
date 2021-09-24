package com.busticketbooking.dto;

import java.time.LocalTime;
import java.util.Date;

import com.busticketbooking.entity.Route;

import lombok.Data;

@Data
public class BusDto {

	private Long id;

	private String name;

	private String busType;

	private int numberOfSeats;

	private String routeName;

	private Route route;
	
	private Date date;
	
	private String fromLocation;
	
	private String toLocation;
	
	private LocalTime departureTime;
	
	private LocalTime arrivalTime;
	private int fare;
}
