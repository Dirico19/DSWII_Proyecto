package com.cibertec.edu.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cibertec.edu.entities.Error;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Error> manejarNotFoundException(EntityNotFoundException e, WebRequest webRequest) {
		Error error = new Error(new Date(), e.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Error> manejarIllegalArgumentException(IllegalArgumentException e, WebRequest webRequest) {
		Error error = new Error(new Date(), e.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> manejarGlobalException(Exception e, WebRequest webRequest) {
		Error error = new Error(new Date(), e.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> errores = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String campo = ((FieldError) error).getField();
			String mensaje = error.getDefaultMessage();
			errores.put(campo, mensaje);
		});
		return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
	}
	
}
