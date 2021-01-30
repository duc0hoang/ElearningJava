package com.myclass.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();

		List<String> listError = new ArrayList<String>();
		for (FieldError fieldError : fieldErrors) {
			listError.add(fieldError.getDefaultMessage());
		}

		Map<String, Object> body = new LinkedHashMap<String, Object>();
		body.put("timestamp", new Date());
		body.put("status", status.value());
		body.put("errors", listError);

		return new ResponseEntity<Object>(body, headers, status);
	}
}
