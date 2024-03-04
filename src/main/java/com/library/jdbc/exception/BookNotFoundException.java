package com.library.jdbc.exception;

public class BookNotFoundException extends LibraryServiceException {
	
	private static final long serialVersionUID = -5652787299813752587L;

	public BookNotFoundException(String id) {
		super("Cannot find book by the given id: " + id);		
	}
}
