package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import dto.EventDTO;
import dto.ParticipantDTO;
import entity.Event;
import entity.Participant;
import exception.EventRegistrationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import repository.EventRepository;
import repository.ParticipantRepository;

@Service("eventService")
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
	
          private final EventRepository eventRepository ;
	      private final ParticipantRepository participantRepository ;
	      private final ModelMapper modelMapper;
	      
		@Override
		  public List<EventDTO> fetchEventsScheduled(String eventDate) {
			  
			 LocalDate localDate = LocalDate.parse(eventDate);
			  List<Event> events =   eventRepository.findByEventDate(localDate);
			       
			  if(events.isEmpty()) {
				    throw new EventRegistrationException("there is not event Scheduled for event provided date");
			  }
			  
			  
			  List<EventDTO> result = new ArrayList<>();

             for (Event e: events) {
            	 
            	 List<Participant> females=  e.getParticipants()
            			                    .stream()
            			                    .filter(p->"female".equalsIgnoreCase(p.getGender()))
            			                    .toList();
            	 if(females.isEmpty()) {
            		 throw new EventRegistrationException("not female Participants are Available at this Venue");
            	 }
            	 
            	 EventDTO dto = modelMapper.map(e,EventDTO.class);
            	 dto.setParticipants(females.stream()
            			                     .map(p ->modelMapper.map(p, ParticipantDTO.class))
            			                     .toList());
            	 
                 result.add(dto);
             }
			    return  result ;
			
		  }
		  @Override
		    @Transactional
		  public Integer cancelRegistration(List<String> emailIds) {
			    
			int count = 0;
			 
			for(String email : emailIds) {
				
				participantRepository.findByEmailId(email).orElseThrow(()->
				                new EventRegistrationException("there is no participant available with this email id"));
				
				count += participantRepository.remove(email);
			}
			
			return count ;
		  }
	      
	      
	      
	      
	      
	      
	      
 
}
