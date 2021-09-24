package com.busticketbooking.dto;

import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

import com.busticketbooking.entity.Bus;

import lombok.Data;

@Data
public class RouteDto {
	
	private long routeId;
	private String fromLocation;
	private String toLocation;
	private String routeName;
	private int distance;
	private Set<Bus> bus;
	
}