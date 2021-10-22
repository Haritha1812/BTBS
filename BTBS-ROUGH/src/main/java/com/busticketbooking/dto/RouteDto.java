package com.busticketbooking.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {

	private long routeId;

	@NotNull(message = "From Location should not be empty")
	private String fromLocation;

	@NotNull(message = "To Location should not be empty")
	private String toLocation;

	@NotNull(message = "Route name should not be empty")
	private String routeName;

	@Min(value = 0, message = "Distance should be valid")
	private int distance;

	@Override
	public String toString() {
		return "RouteDto [routeId=" + routeId + ", fromLocation=" + fromLocation + ", toLocation=" + toLocation
				+ ", routeName=" + routeName + ", distance=" + distance + "]";
	}

}