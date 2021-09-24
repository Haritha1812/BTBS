package com.busticketbooking.entity;

import java.time.LocalTime;
import java.util.Date;

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

@Entity
@Data
@Table(name = "bookticket_master")
public class BookTicket {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name ="bill_amount" ,nullable=false)
	private int billAmount;
	
	  
	@ManyToOne(cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "bus_id", foreignKey = @ForeignKey(name = "FK_Book_busId"))

	private Bus bus;

	@ManyToOne(cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "cus_id", foreignKey = @ForeignKey(name = "FK_Book_cusId"))

	private Customer customer;

}
