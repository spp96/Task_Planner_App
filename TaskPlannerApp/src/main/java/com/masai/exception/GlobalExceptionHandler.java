package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler { 

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> UserExceptionHandler(UserException user, WebRequest request){
		ErrorDetails findError = new ErrorDetails();
		findError.setTimeStamp(LocalDateTime.now());
		findError.setMessage(user.getMessage());
		findError.setDetails(request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(findError,HttpStatus.BAD_REQUEST);
			
	}
	
	@ExceptionHandler(SprintException.class)
	public ResponseEntity<ErrorDetails> sprintExceptionHandler(SprintException sprint, WebRequest request){
		ErrorDetails findError = new ErrorDetails();
		findError.setTimeStamp(LocalDateTime.now());
		findError.setMessage(sprint.getMessage());
		findError.setDetails(request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(findError,HttpStatus.BAD_REQUEST);
			
	}
	
	@ExceptionHandler(TaskException.class)
	public ResponseEntity<ErrorDetails> taskExceptionHandler(TaskException task, WebRequest request){
		ErrorDetails findError = new ErrorDetails();
		findError.setTimeStamp(LocalDateTime.now());
		findError.setMessage(task.getMessage());
		findError.setDetails(request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(findError,HttpStatus.BAD_REQUEST);
			
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception other, WebRequest req){
		ErrorDetails findError = new ErrorDetails();
		findError.setTimeStamp(LocalDateTime.now());
		findError.setMessage(other.getMessage());
		findError.setDetails(req.getDescription(false));
				
		return new ResponseEntity<ErrorDetails>(findError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
