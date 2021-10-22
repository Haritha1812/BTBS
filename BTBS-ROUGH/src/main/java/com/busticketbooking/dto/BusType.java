package com.busticketbooking.dto;

import java.time.LocalTime;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.busticketbooking.entity.Route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusType {

	private long id;
	
	@NotNull(message ="Bus type should not be empty")
	private String busType;
}
