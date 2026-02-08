package entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "participant",
            indexes = {
            		@Index(name =  "idx_participant_email", columnList = "emailId"),
            		@Index(name = "idx_participant_registrationDate", columnList = "registrationDate")
            })
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer participantId;
    
    
    @Column(name = "participant_name", length = 100)
    private String name;
    
    @Column(name = "emailId", length = 100, unique = true)
    private String emailId;
    
    @Column(name = "gender", length = 100)
    private String gender;
    
    @CreationTimestamp
    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}