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

import com.library.jdbc.service.BookService;
import com.library.jdbc.service.pojo.Book;
import com.library.jdbc.utils.LibraryHelper;

@RestController
@RequestMapping("/api")
public class BookController {
	
	public static final Logger log =  LoggerFactory.getLogger(BookController.class);
		
	@Value(("${page:0}"))
	int pageNumberDefaultValue;
	
	@Value(("${size:10}"))
	int pageSizeDefaultValue;	
	
	@Value(("${sizemax:200}"))
	int pageSizeMaxValue;
	
	BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") String id) {
	    return new ResponseEntity<>(bookService.getBook(id), HttpStatus.OK);
	}	
	
	@DeleteMapping("/books/{id}")
	ResponseEntity<Book> deleteBook(@PathVariable String id) {
		bookService.deleteBook(id);
		return ResponseEntity.noContent().build();
	}	
	
	@PostMapping("/books")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Book> addBook(@RequestBody @Validated Book book) {		
		book = bookService.createBook(book);
		return new ResponseEntity<>(book, HttpStatus.CREATED);
	}
	
	@PutMapping("/books")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Book> overriteBook(@RequestBody @Validated Book book) {
		return new ResponseEntity<>(bookService.updateBook(book), HttpStatus.OK);
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks(
			@RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String author,
			@RequestParam(required = false) String isbn) {
		Pageable pageable = LibraryHelper.getPageable(
				page, 
				size, 
				pageNumberDefaultValue, 
				pageSizeDefaultValue, 
				pageSizeMaxValue);
		List<Book> books;
		if (title != null && !title.isBlank()) {
			books = bookService.getBooksByTitle(title, pageable);
		} else if (isbn != null && !isbn.isBlank()) {
			books = bookService.getBooksByIsbn(isbn, pageable);
		} else if (author != null && !author.isBlank()) {
			books = bookService.getBooksByAuthorLastName(author, pageable);
		} else {
			log.info("No any recognized parameters passed to getBooks method, "
					+ "return all books with page {} and page size {}", 
					pageable.getPageNumber(), pageable.getPageSize());
			books = bookService.getBooks(pageable);
		}
		
		if (books.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(books, HttpStatus.OK);
	}		
}
