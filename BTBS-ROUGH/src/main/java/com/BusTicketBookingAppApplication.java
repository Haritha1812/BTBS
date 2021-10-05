package com;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class BusTicketBookingAppApplication {
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}

	private static final Logger logger = LogManager.getLogger(BusTicketBookingAppApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(BusTicketBookingAppApplication.class, args);
		logger.info("Application Started");
	}
	
	
}
