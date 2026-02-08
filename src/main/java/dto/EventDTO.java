package dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
	
	@NotEmpty
    private Integer eventId;
	
	@NotBlank
    private String name;
	
    private LocalDate eventDate;
    
    private String venue;
    
    @Min(value =0)
    @Max(value = 10)
    private Integer maxCount;
    private List<ParticipantDTO> participants;
}