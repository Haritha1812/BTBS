package com.busticketbooking.entity;

import java.util.Date;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "bookticket_master")
@AllArgsConstructor
@NoArgsConstructor
public class BookTicket {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "bill_amount", nullable = false)
	private long billAmount;

	@Column(name = "booking_status", nullable = false)
	private String bookingStatus;

	@Column(name = "booking_date", nullable = false)
	private Date bookingDate;

	@Column(name = "no_of_tickets", nullable = false)
	private long numberOfTickets;

	@ManyToOne()
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "bus_id", foreignKey = @ForeignKey(name = "FK_Book_busId"))

	private Bus bus;

	@ManyToOne()
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "cus_id", foreignKey = @ForeignKey(name = "FK_Book_cusId"))

	private Customer customer;

	@Override
	public String toString() {
		return "BookTicket [id=" + id + ", billAmount=" + billAmount + "]";
	}

	public BookTicket(long id, long billAmount, String bookingStatus, Date bookingDate, long numberOfTickets) {
		super();
		this.id = id;
		this.billAmount = billAmount;
		this.bookingStatus = bookingStatus;
		this.bookingDate = bookingDate;
		this.numberOfTickets = numberOfTickets;
	}

}
