package com.busticketbooking.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.busticketbooking.exception.DatabaseException;
import com.busticketbooking.exception.DuplicateEmailException;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.exception.NullValueException;


@ControllerAdvice
public class ExceptionHandlerUtil {
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
	
	//database Exception
	@ExceptionHandler(DatabaseException.class)

	public ResponseEntity<String> databaseException(DatabaseException e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	//Null value exception
	@ExceptionHandler(NullValueException.class)

	public ResponseEntity<String> nullValueException(NullValueException e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
	}

}
