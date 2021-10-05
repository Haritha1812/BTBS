package com.busticketbooking.dto;

import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

import com.busticketbooking.entity.Bus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {

	private long routeId;
	private String fromLocation;
	private String toLocation;
	private String routeName;
	private int distance;

	@Override
	public String toString() {
		return "RouteDto [routeId=" + routeId + ", fromLocation=" + fromLocation + ", toLocation=" + toLocation
				+ ", routeName=" + routeName + ", distance=" + distance + "]";
	}

}