package service;

import java.util.List;

import dto.EventDTO;

public interface EventService {

	    List<EventDTO> fetchEventsScheduled(String eventDate);
	    
	    Integer cancelRegistration(List<String> emailIds);


}
