package com.library.jdbc.exception;

public class BookAlreadyExistException extends LibraryServiceException {
	
	private static final long serialVersionUID = -5652787299813752589L;

	public BookAlreadyExistException(String id) {
		super("Book with the the given id: " + id + " already exist");		
	}
}
