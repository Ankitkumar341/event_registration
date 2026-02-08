package utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import exception.EventRegistrationException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	private static final Logger LOGGER = LogManager.getLogger(ExceptionControllerAdvice.class);
	 @Autowired  
	 private Environment environment;
	   
	@ExceptionHandler(EventRegistrationException.class)
	public ResponseEntity<ErrorInfo>  eventRegistrationExceptionHnadler (EventRegistrationException ex){
		
		   LOGGER.error("EventRegistration Exception : {}" , ex.getMessage());
		   
		   String message = environment.getProperty("Service_EVENTS_UNAVAILABLE",ex.getMessage());
		   
		   ErrorInfo errorInfo = new ErrorInfo("ERR001", message);
		   
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);
	}
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler (Exception ex){
		LOGGER.error("General Exception : {}" + ex.getMessage());
		String message = environment.getProperty("Service_GENERAL_ERROR"+ ex.getMessage());
		 ErrorInfo errorInfo = new ErrorInfo("ERR002", message);
		  return new  ResponseEntity<>(errorInfo , HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> validatorExceptionHandler(MethodArgumentNotValidException ex){
		LOGGER.error("Validation Exception : {}" + ex.getMessage());
		String message = ex.getBindingResult().getFieldError().getDefaultMessage();	
		 ErrorInfo errorInfo = new ErrorInfo( "ERR003", message);
		  return new  ResponseEntity<>(errorInfo , HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	

}
