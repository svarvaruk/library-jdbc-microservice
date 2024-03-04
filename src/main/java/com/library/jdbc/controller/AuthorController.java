package com.library.jdbc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.library.jdbc.service.AuthorService;
import com.library.jdbc.service.pojo.Author;
import com.library.jdbc.utils.LibraryHelper;

@RestController
@RequestMapping("/api")
public class AuthorController {

	public static final Logger log =  LoggerFactory.getLogger(AuthorController.class);
	
	@Value(("${page:0}"))
	int pageNumberDefaultValue;
	
	@Value(("${size:10}"))
	int pageSizeDefaultValue;	
	
	@Value(("${sizemax:200}"))
	int pageSizeMaxValue;
	
	AuthorService authorService;
		
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@GetMapping("/authors/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable("id") String id) {
	    return new ResponseEntity<>(authorService.getAuthor(id), HttpStatus.OK);
	}	
	
	@DeleteMapping("/authors/{id}")
	ResponseEntity<Author> deleteAuthor(@PathVariable String id) {
		authorService.deleteAuthor(id);
		return ResponseEntity.noContent().build();
	}	
	
	@PostMapping("/authors")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Author> addAuthor(@RequestBody @Validated Author author) {		
		authorService.createAuthor(author);
		return new ResponseEntity<>(author, HttpStatus.CREATED);
	}
	
	@PutMapping("/authors")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Author> overriteAuthor(@RequestBody @Validated Author author) {
		return new ResponseEntity<>(authorService.updateAuthor(author), HttpStatus.OK);
	}
	
    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAuthors(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
    	List<Author> authors;
		Pageable pageable = LibraryHelper.getPageable(
				page, 
				size, 
				pageNumberDefaultValue, 
				pageSizeDefaultValue, 
				pageSizeMaxValue);    	
		if (lastName != null && !lastName.isBlank()
				&& (firstName == null || firstName.isBlank())) {
			log.info("Last Name has been provided: {}", lastName);    	
			authors = authorService.getAuthorsByLastName(lastName, pageable);
		} else 	if (firstName != null && !firstName.isBlank()
				&& (lastName == null || lastName.isBlank())) {
			log.info("First Name has been provided: {}", firstName);    	
			authors = authorService.getAuthorsByFirstName(firstName, pageable);
		} else if (lastName != null && !lastName.isBlank()
				&& firstName != null && !firstName.isBlank()) {
			log.info("First Name: {} and Last Name: {} were provided", firstName, lastName);
			authors = authorService.getAuthorsByFirstAndLastName(firstName, lastName, pageable);
		} else {
			log.info("No any recognized parameters passed to getAuthors method, "
					+ "return all authors with page {} and page size {}", 
					pageable.getPageNumber(), pageable.getPageSize());
			authors = authorService.getAuthors(pageable);
		}
				
		if (authors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(authors, HttpStatus.OK);		
    }	
}
