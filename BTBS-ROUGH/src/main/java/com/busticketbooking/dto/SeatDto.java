package com.busticketbooking.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.busticketbooking.entity.Bus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {
	private long id;

	@Min(value = 0, message = "Seat number should be valid")
	private int seatNumber;

	@NotNull(message = "Seat status should not be empty")
	private String seatStatus;

	private Bus bus;

	@Override
	public String toString() {
		return "SeatDto [id=" + id + ", seatNumber=" + seatNumber + ", seatStatus=" + seatStatus + "]";
	}

}
