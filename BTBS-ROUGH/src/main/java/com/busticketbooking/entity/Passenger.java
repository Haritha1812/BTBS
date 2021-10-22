package com.busticketbooking.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Data
@Entity
@Table(name = "passenger")
public class Passenger {

	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "passenger_name", nullable = false)
	private String name;

	@Column(name = "passenger_gender", nullable = false)
	private String gender;

	@Column(name = "passenger_age", nullable = false)
	private int age;

	@Column(name = "seat_number", nullable = false)
	private int seatNumber;

	@ManyToOne()
	@JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "FK_CUS_PASId"))
	private Customer customer;

	@ManyToOne()
	@JoinColumn(name = "bus_id", foreignKey = @ForeignKey(name = "FK_BUS_PASId"))
	private Bus bus;


}
