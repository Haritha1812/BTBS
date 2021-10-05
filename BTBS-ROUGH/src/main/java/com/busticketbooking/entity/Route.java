package com.busticketbooking.entity;

import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "route_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long routeId;

	@Column(name = "from_location", nullable = false)
	private String fromLocation;

	@Column(name = "to_location", nullable = false)
	private String toLocation;

	@Column(name = "route_name", nullable = false)
	private String routeName;

	@Column(name = "distance", nullable = false)
	private int distance;

	@JsonIgnore
	@OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Bus> bus;

	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", fromLocation=" + fromLocation + ", toLocation=" + toLocation
				+ ", routeName=" + routeName + ", distance=" + distance + "]";
	}

}
