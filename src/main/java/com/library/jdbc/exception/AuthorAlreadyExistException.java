package com.library.jdbc.exception;

public class AuthorAlreadyExistException extends LibraryServiceException {
	
	private static final long serialVersionUID = -5652787299813752588L;

	public AuthorAlreadyExistException(String id) {
		super("Author with the the given id: " + id + " already exist");		
	}
}
