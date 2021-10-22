package com.busticketbooking.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CustomerDto {

	private long id;
	private String name;

	@NotNull(message = "Password should not be empty")
	private String password;

	@NotNull(message = "Mobile number should not be empty")
	private String mobileNumber;

	@NotNull(message = "Email should not be empty")
	private String email;
}
