package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import entity.Participant;
import jakarta.transaction.Transactional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant,Integer > {
	
	Optional<Participant>  findByEmailId(String emailId);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Participant p where p.emailId = :emailId ")
     int remove(@Param("emailId")String emailId);
	
	
	

}
