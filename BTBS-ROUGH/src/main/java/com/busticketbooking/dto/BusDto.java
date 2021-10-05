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

	private String routeName;

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
				+ ", routeName=" + routeName + ", date=" + date + ", fromLocation=" + fromLocation + ", toLocation="
				+ toLocation + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", fare=" + fare
				+ ", getId()=" + getId() + ", getName()=" + getName() + ", getBusType()=" + getBusType()
				+ ", getNumberOfSeats()=" + getNumberOfSeats() + ", getRouteName()=" + getRouteName() + ", getRoute()="
				+ getRoute() + ", getDate()=" + getDate() + ", getFromLocation()=" + getFromLocation()
				+ ", getToLocation()=" + getToLocation() + ", getDepartureTime()=" + getDepartureTime()
				+ ", getArrivalTime()=" + getArrivalTime() + ", getFare()=" + getFare() + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}
}
