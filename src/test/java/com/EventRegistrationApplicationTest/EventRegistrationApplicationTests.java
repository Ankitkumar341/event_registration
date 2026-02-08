package com.EventRegistrationApplicationTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.core.env.Environment;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import api.EventAPI;
import exception.EventRegistrationException;
import service.EventService;
import utility.ExceptionControllerAdvice;

@ExtendWith(MockitoExtension.class)
class EventRegistrationApplicationTests {

    private MockMvc mockMvc;

    @Mock
    private EventService eventService;

    @Mock
    private Environment environment;

    @InjectMocks
    private EventAPI eventAPI;

    private final String SERVICE_EVENTS_UNAVAILABLE = "There are no events scheduled for the provided date!";

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(eventAPI, "eventService", eventService);
        
        ExceptionControllerAdvice advice = new ExceptionControllerAdvice();
        ReflectionTestUtils.setField(advice, "environment", environment);
        
      
        when(environment.getProperty(eq("Service_EVENTS_UNAVAILABLE"), anyString()))
             .thenReturn(SERVICE_EVENTS_UNAVAILABLE);
        
        mockMvc = MockMvcBuilders.standaloneSetup(eventAPI)
                                .setControllerAdvice(advice)
                                .build();
    }
  



    @Test
    void fetchEventScheduledParticipantsUnavailable() throws Exception {
        Mockito.when(eventService.fetchEventsScheduled("2026-02-10"))
               .thenThrow(new EventRegistrationException(SERVICE_EVENTS_UNAVAILABLE));

        mockMvc.perform(get("/event-api/event/2026-02-10"))
                .andDo(print())
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.errorCode").value("ERR001"))
               .andExpect(jsonPath("$.errorMessage").value(SERVICE_EVENTS_UNAVAILABLE));
    }
    
    
    

}
