package com.busticketbooking.dto;

import java.time.LocalTime;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.busticketbooking.entity.Route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDto {

	private Long id;

	@NotNull(message = "Bus name should not be empty")
	private String name;

	@NotNull(message = "Bus type should not be empty")
	private String busType;

	@Min(value = 0, message = "Seats should be minimum of 0")
	private int numberOfSeats;

	private Route route;

	@NotNull(message = "Bus date should not be empty")
	private Date date;

	private LocalTime departureTime;

	private LocalTime arrivalTime;

	@Min(value = 0, message = "Fare should be minimum of 0")
	private int fare;

	@Override
	public String toString() {
		return "BusDto [id=" + id + ", name=" + name + ", busType=" + busType + ", numberOfSeats=" + numberOfSeats
				+ ", date=" + date + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", fare="
				+ fare + "]";
	}

}
