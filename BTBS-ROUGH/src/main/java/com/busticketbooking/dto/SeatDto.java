package com.busticketbooking.dto;

import com.busticketbooking.entity.Bus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {
	private long id;

	private int seatNumber;

	private String seatStatus;

	private Bus bus;

	@Override
	public String toString() {
		return "SeatDto [id=" + id + ", seatNumber=" + seatNumber + ", seatStatus=" + seatStatus + "]";
	}



}
