package com.intern.aws.s3restapi.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.amazonaws.SdkClientException;

@ControllerAdvice // Global exception handler - interceptor of exceptions
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	// @ExceptionHandler annotation indicates which type of Exception we want to handle
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), 
																	HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
																	ex.getMessage(), 
																	request.getDescription(false));
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;
	}
	
	@ExceptionHandler(SdkClientException.class)
	public final ResponseEntity<Object> handleSdkClientException(SdkClientException ex, WebRequest request)  {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	Integer.toString(HttpStatus.NOT_FOUND.value()), 
																	HttpStatus.NOT_FOUND.getReasonPhrase(), 
																	ex.getMessage(),
																	request.getDescription(false));
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
		return responseEntity;
	}
	
	@ExceptionHandler(UnableToProcessInputFileException.class)
	public final ResponseEntity<Object> handleUnableToProcessInputFileException(UnableToProcessInputFileException ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	Integer.toString(HttpStatus.BAD_REQUEST.value()), 
																	HttpStatus.BAD_REQUEST.getReasonPhrase(), 
																	ex.getMessage(), 
																	request.getDescription(false));
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
	
}
