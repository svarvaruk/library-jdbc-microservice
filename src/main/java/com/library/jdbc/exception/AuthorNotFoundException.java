package com.library.jdbc.exception;

public class AuthorNotFoundException extends LibraryServiceException {
	
	private static final long serialVersionUID = -5652787299813752585L;

	public AuthorNotFoundException(String id) {
		super("Cannot find author by the given id: " + id);		
	}
}
