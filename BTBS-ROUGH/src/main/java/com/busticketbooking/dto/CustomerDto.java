package com.busticketbooking.dto;


import com.busticketbooking.entity.BookTicket;

import lombok.Data;

@Data
public class CustomerDto {


	private long id;
	private String name;
    
	private String password;
    
	private String mobileNumber;
	private String email;
//   
//    private BookTicket bookTicket;
}
