package com.busticketbooking.dto;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {
	private long id;

	private String seatName;

	private String seatStatus;

	private Bus bus;

	@Override
	public String toString() {
		return "SeatDto [id=" + id + ", seatName=" + seatName + ", seatStatus=" + seatStatus + "]";
	}

}
