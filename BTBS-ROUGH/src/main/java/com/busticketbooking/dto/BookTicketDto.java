package com.busticketbooking.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.ForeignKey;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.busticketbooking.entity.Bus;

import lombok.Data;

public class BookTicketDto {
/*id
+Arrival Station}
+Departure Station
+Date
+BusType
+RouteId
+Seatno
+Fare
+PassengerId
+BusId
*
*/

	private long id;
	private long arrivalLocation;
	private long departureLocation;
	private Date bookingDate;
	private int NumberOfSeats;
	private Bus bus;

}