package com.busticketbooking.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.busticketbooking.exception.DatabaseException;
import com.busticketbooking.exception.DuplicateEmailException;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.exception.NullValueException;

@ControllerAdvice
public class ExceptionHandlerUtil extends ResponseEntityExceptionHandler{
	// exception for Duplicate Id insertion..
	@ExceptionHandler(DuplicateEmailException.class)

	public ResponseEntity<String> duplicateIdFound(DuplicateEmailException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// exception for Id not Found ..
	@ExceptionHandler(IdNotFoundException.class)

	public ResponseEntity<String> idNotFound(IdNotFoundException e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	// database Exception
	@ExceptionHandler(DatabaseException.class)

	public ResponseEntity<String> databaseException(DatabaseException e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// Null value exception
	@ExceptionHandler(NullValueException.class)

	public ResponseEntity<String> nullValueException(NullValueException e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
	}
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public Map<String, String> handleValidationExceptions(
//	  MethodArgumentNotValidException ex) {
//	    Map<String, String> errors = new HashMap<>();
//	    ex.getBindingResult().getAllErrors().forEach((error) -> {
//	        String fieldName = error.getObjectName();
//	        String errorMessage = error.getDefaultMessage();
//	        errors.put(fieldName, errorMessage);
//	    });
//	    return errors;
//	}
}
