package com.library.jdbc.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.library.jdbc.service.pojo.Author;

public interface LibraryAuthorDAO {
	String create(Author author);
	Author get(String id);
	boolean isAuthorExist(String id);
	Author update(Author author);
	void delete(String id);
	List<Author> getAuthors(Pageable pageable);
	List<Author> getAuthorsByFirstName(String firstName, Pageable pageable);
	List<Author> getAuthorsByLastName(String lastName, Pageable pageable);
	List<Author> getAuthorsByFirstAndLastName(String firstName, String lastName, Pageable pageable);
	
}
