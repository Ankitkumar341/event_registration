package api;

import java.io.IOException;
import java.util.List;

import org.hibernate.event.service.spi.EventListenerRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.EventDTO;
import exception.EventRegistrationException;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import service.EventService;
import service.EventServiceImpl;
import utility.ExceptionControllerAdvice;


@RestController
@RequestMapping("/event-api")
@Validated
@RequiredArgsConstructor
public class EventAPI {

//    private final ExceptionControllerAdvice exceptionControllerAdvice;
	
	
	     private final EventService eventService ;
	     @Autowired
	      private final  Environment  environment ;

   
	     @GetMapping("/event/{eventDate}")
	     public ResponseEntity<List<EventDTO>> fetchEventsScheduled(@PathVariable @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") String eventDate){
	    	  try {
	    	        List<EventDTO> events = eventService.fetchEventsScheduled(eventDate);
	    	        return ResponseEntity.ok(events);
	    	    } catch (EventRegistrationException ex) {
	    	        throw ex;  	    	    }
	     }
	     
	
	     
	     
	     @DeleteMapping("/events")
	     public ResponseEntity<String> cancelRegistration(@RequestParam  @Size(min = 1,max =2) List<String>emailIds){
	    	 int removed = eventService.cancelRegistration(emailIds);
	    	 return ResponseEntity.ok(environment.getProperty("API.DELETE_SUCCESS")+ " " +removed);
	                                                	  
	                                                  }
{
	    	 
	     }
	    

}
