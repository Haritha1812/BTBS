package com.busticketbooking.service.Impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.busticketbooking.dao.BookTicketDao;
import com.busticketbooking.dto.BookTicketDto;
import com.busticketbooking.entity.BookTicket;
import com.busticketbooking.service.BookTicketService;



@RunWith(SpringRunner.class)
@SpringBootTest
class BookTicketTest {
	
@MockBean
BookTicketService bookticketService;
	

static final List<BookTicket> bookticket_data = Stream.of(new BookTicket(1,550,"Confirmed",new Date(),2), new BookTicket(1,550,"Confirmed",new Date(),2))	.collect(Collectors.toList());
	
    @Test
	void getAllTickets() {
		when(bookticketService.getAllTickets()).thenReturn(bookticket_data);
		assertEquals(2, bookticketService.getAllTickets().size());
	}
    
    @Test
    void getTicketbyId() {
    	BookTicket bookTicket = new BookTicket(1L, 450, "Confirmed", new Date(), 5);
    	when(bookticketService.getTicketById(1L)).thenReturn(bookTicket);
    	assertEquals(bookTicket, bookticketService.getTicketById(Long.valueOf(1)));
    }
    
    @Test
    void addTicket() {
    	BookTicketDto bookTicket = new BookTicketDto(Long.valueOf(1), 450, "Confirmed", new Date(), 5);
    	bookticketService.addTicket(bookTicket);
          verify(bookticketService,times(1)).addTicket(bookTicket);
    	
    }
  
}
