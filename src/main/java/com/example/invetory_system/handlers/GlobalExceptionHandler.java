package com.example.invetory_system.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.invetory_system.exceptions.InvalidInputException;
import com.example.invetory_system.exceptions.ModelNotFoundException;
import com.example.invetory_system.helpers.ResponseValue;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ResponseValue> invalidInputException(InvalidInputException exception) {
		return ResponseEntity.badRequest().body(new ResponseValue("Invalid fields received"));
	}

	@ExceptionHandler(ModelNotFoundException.class)
	public ResponseEntity<ResponseValue> modelNotFoundExcpetion(ModelNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseValue("Product not found"));
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ResponseValue> missingServletRequestParameterException(
			MissingServletRequestParameterException exception) {
		return ResponseEntity.badRequest().body(new ResponseValue("Invalid fields received"));
	}
}
