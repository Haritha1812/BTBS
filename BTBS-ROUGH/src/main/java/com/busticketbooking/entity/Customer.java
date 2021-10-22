package com.busticketbooking.entity;

import java.util.List;

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

import lombok.Data;

@Entity
@Table(name = "customer")
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

	@Column(name = "customer_email", nullable = false, unique = true)
	private String email;
	


}
