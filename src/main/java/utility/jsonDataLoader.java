//package utility;
//import java.io.InputStream;
//import java.util.List;
//
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
//
//import entity.Event;
//import lombok.RequiredArgsConstructor;
//import repository.EventRepository;
//import tools.jackson.core.type.TypeReference;
//import tools.jackson.databind.ObjectMapper;
//
//@Component
//@RequiredArgsConstructor
//public class jsonDataLoader {
//	
//		private final EventRepository eventRepository ;
//		private final ObjectMapper mapper ;
//		
//		
//		
//		public void load() throws Exception {
//			InputStream is = new ClassPathResource("InputData.txt").getInputStream();
//			
//			List<Event> event = mapper.readValue(is, new TypeReference<List<Event>>() {});
//			 eventRepository.saveAll(event);
//			
//		}
//		
//}
