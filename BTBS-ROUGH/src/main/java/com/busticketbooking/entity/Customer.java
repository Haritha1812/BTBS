package com.busticketbooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "customer_master")
@Data
public class Customer {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "customer_name", nullable = false)
	private String name;

	@Column(name = "customer_password", nullable = false)
	private String password;

	@Column(name = "customer_mobileNumber", nullable = false)
	private String mobileNumber;

	@Column(name = "customer_email", nullable = false,unique = true)
	private String email;

}
