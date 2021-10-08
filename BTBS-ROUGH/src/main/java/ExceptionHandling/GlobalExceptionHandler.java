package ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.DatabaseException;
import com.busticketbooking.response.HttpResponseStatus;

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
	
}
