package core.exceptions;

import core.exceptions.abstracts.BaseExceptionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

	    @NotNull
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
	                                                                  @NotNull final HttpHeaders headers,
	                                                                  @NotNull final HttpStatus status,
	                                                                  @NotNull final WebRequest request) {
	        final Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getAllErrors().forEach(error -> {
	            final String fieldName = ((FieldError) error).getField();
	            final String errorMessage = error.getDefaultMessage();
	            errors.put(fieldName, errorMessage);
	        }); 

	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }

	    
	    @ExceptionHandler(BaseExceptionModel.class)
	    public ResponseEntity<?> baseExceptionModelExceptionHandler(final BaseExceptionModel exception) {
	    	return convertCustomException(exception, exception.getBaseStatusCode()); }


	    private ResponseEntity<?> convertCustomException(final BaseExceptionModel exceptionModel, final HttpStatus httpStatus) {
	        
	    	final HttpHeaders headers = new HttpHeaders();
	       
	        headers.add("errorFlag", "true");
	       
	        final Map<String, String> body = new HashMap<>();

	        body.put("statusCode", String.valueOf(exceptionModel.getBaseStatusCode().value()));
	        body.put("errorCode", exceptionModel.getErrorCode()); 
	        body.put("errorMessage", exceptionModel.getErrorMessage()); 
	        body.put("errorDescription", exceptionModel.getErrorDescription());
	        body.put("timestamp",exceptionModel.getTimeStamp().toString());
	        return new ResponseEntity<>(body, headers, httpStatus); }
	
}
