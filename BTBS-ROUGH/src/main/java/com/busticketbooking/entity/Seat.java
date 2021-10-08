package com.busticketbooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "seat")
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "seat_name",nullable = false)
	private int seatNumber;

	@Column(name = "seat_status", nullable = false)
	private String seatStatus;

	@ManyToOne()
	@JoinColumn(name = "bus_id", foreignKey = @ForeignKey(name = "FK_BUS_SEATID"))
	private Bus bus;

	@Override
	public String toString() {
		return "Seat [id=" + id + ", seatNumber=" + seatNumber + ", seatStatus=" + seatStatus + "]";
	}



}
