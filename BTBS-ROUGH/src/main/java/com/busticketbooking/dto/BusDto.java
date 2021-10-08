package com.busticketbooking.dto;

import java.time.LocalTime;
import java.util.Date;

import com.busticketbooking.entity.Route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDto {

	private Long id;

	private String name;

	private String busType;

	private int numberOfSeats;

	private Route route;

	private Date date;

	private String fromLocation;

	private String toLocation;

	private LocalTime departureTime;

	private LocalTime arrivalTime;
	private int fare;
	
	@Override
	public String toString() {
		return "BusDto [id=" + id + ", name=" + name + ", busType=" + busType + ", numberOfSeats=" + numberOfSeats
				+ ", date=" + date + ", fromLocation=" + fromLocation + ", toLocation=" + toLocation
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", fare=" + fare + "]";
	}

	
}
