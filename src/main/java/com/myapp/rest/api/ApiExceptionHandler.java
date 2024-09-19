package com.myapp.rest.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {


	

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason="Not Found")
@ExceptionHandler(ProductNotFoundException.class)
public void notFoundHandler() {
	
}


@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason="Already Exists")
@ExceptionHandler(ProductAlreadyExistsException.class)
public void alreadyExistsHandler() {
	
}

}