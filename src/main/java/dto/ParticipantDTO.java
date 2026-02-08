package dto;

import java.time.LocalDate;
import java.util.List;


import entity.Event;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDTO {

	    @NotNull
	    private Integer participantId;
	    
	    @NotBlank
	    private String name;
	    
	   @Email
	    private String emailId;
	    
	    @NotBlank
	    private String gender;
	   
	    private LocalDate registrationDate;

	    @NotNull
	    private List<Event> event;
}

