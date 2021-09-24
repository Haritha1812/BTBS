package com.busticketbooking.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;




/**
 * @author HarithaP
 *
 */
@Entity
@Data
@Table(name = "bus_master")

public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="bus_name" ,length=30)
	private String name;
	
	@Column(name ="bus_type" ,nullable=false,length=30)
	private String busType;
	
	@Column(name="seats", nullable=false)
	private int numberOfSeats;
	
	@Column(name="route_name",nullable=false,length=30)
     private String routeName;
	

	@Basic
	@Temporal(TemporalType.DATE)
	@Column(name="busjourney_date",nullable=false,length=30)
	private Date date;
	

	@Column(name ="fare" ,nullable=false)
	private int fare;


	@Column(name ="arrival_time" ,nullable=false)
	private LocalTime arrivalTime;


	@Column(name ="departure_time" ,nullable=false)
	private LocalTime  departureTime;

	
	@ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
 	@JoinColumn(name="route_id" , foreignKey = @ForeignKey(name ="FK_BUS_ROUTEID"))
    private Route route;
	
}
