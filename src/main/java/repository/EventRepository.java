package repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Event;

@Repository
public interface EventRepository  extends JpaRepository<Event,Integer>{
	
	

	List<Event> findByEventDate(LocalDate localDate);

}
