package com.example.demo;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import api.EventAPI;
import exception.EventRegistrationException;
import service.EventService;

@SpringBootTest
@WebMvcTest(EventAPI.class)
@EnableWebMvc
class EventRegistrationApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MockMvc mockMvc ;
	
	 @MockitoBean
	 private EventService eventService ;
	
	 private final String SERVICE_EVENTS_UNAVAILABLE = "There are no events scheduled for the provided date!";
	 

	public void fetchEventScheduledParticipantsUnavailable() throws Exception  {
		
		Mockito.when(eventService.fetchEventsScheduled("2026-2-10"))
		.thenThrow(new EventRegistrationException(SERVICE_EVENTS_UNAVAILABLE));
		
	     mockMvc.perform(get("/event-api/events/2026-2-10"))
	     .andExpect(status().isNotFound())
	     .andExpect(jsonPath("$.errorMessege").value(SERVICE_EVENTS_UNAVAILABLE));
	
}
}

