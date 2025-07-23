package com.Springboot.restApi.exception;

import org.springframework.http.HttpStatus;

public class RestApiException extends RuntimeException{

	
	private HttpStatus status;
	private String message;
	public RestApiException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	
	
}
