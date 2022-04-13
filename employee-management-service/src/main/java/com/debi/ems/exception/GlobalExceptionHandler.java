package com.debi.ems.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(NoDataFoundException.class)
	protected ResponseEntity<Object> handleNoDataFoundException(NoDataFoundException ex) {
		LOGGER.error("Inside handleNoDataFoundException:", ex);
		Map<String,String> body=new HashMap<String, String>();
		body.put("message", ex.getLocalizedMessage());
		return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	@Order(Ordered.LOWEST_PRECEDENCE)
	protected ResponseEntity<Object> handleException(Exception ex) {
		LOGGER.error("Inside handleException:", ex);
		Map<String,String> body=new HashMap<String, String>();
		body.put("message", ex.getLocalizedMessage());
		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
	}
}
