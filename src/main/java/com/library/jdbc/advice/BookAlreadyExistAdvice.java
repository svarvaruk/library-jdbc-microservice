package com.library.jdbc.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.library.jdbc.exception.BookAlreadyExistException;

@ControllerAdvice
public class BookAlreadyExistAdvice {
	
	@ResponseBody
	@ExceptionHandler(BookAlreadyExistException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String bookAlreadyExistHandler(BookAlreadyExistException ex) {
		return ex.getMessage();
	}
}
