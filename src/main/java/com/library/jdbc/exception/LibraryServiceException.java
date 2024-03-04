package com.library.jdbc.exception;

public class LibraryServiceException extends RuntimeException {
	
	private static final long serialVersionUID = -5652787299813752586L;

	public LibraryServiceException(String message) {
		super(message);		
	}
	
	public LibraryServiceException(String message, Throwable e) {
		super(message, e);		
	}	
}
