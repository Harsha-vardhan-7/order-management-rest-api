package com.flmfoods.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<String> itemNotFound(ItemNotFoundException itemNotFoundException){
		return new ResponseEntity<>(itemNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InsufficientStockException.class)
	public ResponseEntity<String> handleInsufficientStockException(InsufficientStockException insufficientStockException){
		return new ResponseEntity<>(insufficientStockException.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException){
		return new ResponseEntity<>(illegalArgumentException.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
}
