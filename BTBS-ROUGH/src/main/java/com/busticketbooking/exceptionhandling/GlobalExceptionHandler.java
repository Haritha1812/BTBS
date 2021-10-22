package com.busticketbooking.exceptionhandling;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.DatabaseException;
import com.busticketbooking.response.HttpResponseStatus;

import static com.busticketbooking.util.BusTicketBookingConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessLogicException.class)
	public ResponseEntity<HttpResponseStatus> bussinessException(BusinessLogicException e) {

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	// EXCEPTION HANDLER FOR DATABASEEXCEPTION.
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<HttpResponseStatus> dataBaseException(DatabaseException e) {
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	// MethodArgumentTypeMismatchException
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<HttpResponseStatus> invalidInputArgumentsFound(MethodArgumentTypeMismatchException e) {

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY.value(),e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	// MethodArgumentTypeMismatchException
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<HttpResponseStatus> inputmismatch(HttpMessageNotReadableException e) {

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY.value(),e.getRootCause().toString()),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HttpResponseStatus> handleValidationExceptions(MethodArgumentNotValidException ex) {
	Map<String, String> errors = new HashMap<>();
	List<String > l=new ArrayList<>();
	ex.getBindingResult().getAllErrors().forEach((error) -> {
	String fieldName = error.getObjectName();
	String errorMessage = error.getDefaultMessage();
	l.add(errorMessage);
	errors.put(fieldName, errorMessage);
	});
	return new ResponseEntity<>(
	new HttpResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY.value(),l.get(0)),
	HttpStatus.UNPROCESSABLE_ENTITY);



	}


	
}
