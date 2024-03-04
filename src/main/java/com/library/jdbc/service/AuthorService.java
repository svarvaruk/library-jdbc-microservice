package com.library.jdbc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.library.jdbc.dao.LibraryAuthorDAO;
import com.library.jdbc.exception.AuthorAlreadyExistException;
import com.library.jdbc.exception.AuthorNotFoundException;
import com.library.jdbc.service.pojo.Author;

@Service
public class AuthorService {
	public static final Logger log =  LoggerFactory.getLogger(AuthorService.class);
			
	LibraryAuthorDAO authorRepository;

	public AuthorService(LibraryAuthorDAO authorRepository) {
		this.authorRepository = authorRepository;
	}
	
	public Author getAuthor(String id) {
		return authorRepository.get(id);
	}
	
	public void deleteAuthor(String id) {
		if (authorRepository.isAuthorExist(id)) {
			authorRepository.delete(id);
		} else {
			throw new AuthorNotFoundException(id);
		}
	}
	
	public Author createAuthor(Author author) {
		if(!authorRepository.isAuthorExist(author.getAuthorId())) {
			authorRepository.create(author);
		} else {
			throw new AuthorAlreadyExistException(author.getAuthorId());
		}
		return author;
	}
	
	public Author updateAuthor(Author author) {
		if (authorRepository.isAuthorExist(author.getAuthorId())) {
			authorRepository.update(author);
		} else {
			throw new AuthorNotFoundException(author.getAuthorId());
		}
		return author;
	}	
	
	public List<Author> getAuthors(Pageable pageable) {
		return authorRepository.getAuthors(pageable);
	}
	
	public List<Author> getAuthorsByLastName(String lastName, Pageable pageable) {
		return authorRepository.getAuthorsByLastName(lastName, pageable);
	}
	
	public List<Author> getAuthorsByFirstName(String firstName, Pageable pageable) {
		return authorRepository.getAuthorsByFirstName(firstName, pageable);
	}
	
	public List<Author> getAuthorsByFirstAndLastName(String firstName, String lastName, Pageable pageable) {
		return authorRepository.getAuthorsByFirstAndLastName(firstName, lastName, pageable);
	}	
}